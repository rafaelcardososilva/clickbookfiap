package br.com.clickbook.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.clickbook.exceptions.EmailInvalidException
import br.com.clickbook.exceptions.PasswordInvalidException
import br.com.clickbook.extensions.isValidEmail
import br.com.clickbook.models.RequestState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    val loginState = MutableLiveData<RequestState<FirebaseUser>>()

    fun signIn(email: String, password: String) {

        loginState.value = RequestState.Loading

        if (validateFields(email, password)) {
            mAuth.signInWithEmailAndPassword(
                email,
                password
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        loginState.value = RequestState.Success(mAuth.currentUser!!)
                    } else {
                        loginState.value = RequestState.Error(
                            Throwable(
                                task.exception?.message ?: "Não foi possível realizar a requisição"
                            )
                        )
                    }
                }
        }
    }

    private fun validateFields(email: String, password: String): Boolean {
        if (!email.isValidEmail()) {
            loginState.value = RequestState.Error(EmailInvalidException())
            return false
        }

        if (password.isEmpty()) {
            loginState.value = RequestState.Error(PasswordInvalidException("Informe uma senha"))
            return false
        }

        return true
    }
}