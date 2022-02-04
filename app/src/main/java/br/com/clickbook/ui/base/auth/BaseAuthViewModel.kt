package br.com.clickbook.ui.base.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.clickbook.models.RequestState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class BaseAuthViewModel : ViewModel() {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    val loggedState = MutableLiveData<RequestState<FirebaseUser>>()

    fun isLoggedIn() {
        mAuth.currentUser?.reload()

        val user = mAuth.currentUser
        loggedState.value = RequestState.Loading
        if (user == null) {
            loggedState.value = RequestState.Error(Throwable("Usuário deslogado"))
        } else {
            loggedState.value = RequestState.Success(user)
        }
    }
}