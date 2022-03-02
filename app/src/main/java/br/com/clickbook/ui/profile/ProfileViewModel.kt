package br.com.clickbook.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.clickbook.models.RequestState
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel: ViewModel() {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    val user = mAuth.currentUser
    val loginState = MutableLiveData<RequestState<Boolean>>()

    fun logout() {
        loginState.value = RequestState.Loading
        mAuth.signOut()
        loginState.value = RequestState.Success(true)
    }
}