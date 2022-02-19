package br.com.clickbook.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.clickbook.exceptions.EmailInvalidException
import br.com.clickbook.exceptions.PasswordInvalidException
import br.com.clickbook.extensions.isValidEmail
import br.com.clickbook.models.NewUser
import br.com.clickbook.models.RequestState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel: ViewModel() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    val signUpState = MutableLiveData<RequestState<FirebaseUser>>()

    fun signUp(newUser: NewUser) {
        signUpState.value = RequestState.Loading

        if (validateFields(newUser)) {
            mAuth.createUserWithEmailAndPassword(
                newUser.email ?: "",
                newUser.password ?: ""
            )
                .addOnCompleteListener{ task->
                    if (task.isSuccessful) {
                        signUpState.value = RequestState.Success(mAuth.currentUser!!)
                    } else {
                        signUpState.value = RequestState.Error(
                            Throwable(
                                task.exception?.message ?: "Não foi possível realizar a requisição"
                            )
                        )
                    }
                }
        }
    }

    fun validateFields(newUser: NewUser): Boolean {
        if (newUser.name?.isEmpty() == true) {
            signUpState.value = RequestState.Error(Throwable("Informe o nome do usuário"))
            return false
        }

        if (newUser.email?.isValidEmail() == false) {
            signUpState.value = RequestState.Error(EmailInvalidException())
            return false
        }

        if (newUser.password?.length ?: 0 < 6) {
            signUpState.value =
                RequestState.Error(PasswordInvalidException("Senha com no mínimo 6 caracteres"))
            return false
        }

        return true
    }
}