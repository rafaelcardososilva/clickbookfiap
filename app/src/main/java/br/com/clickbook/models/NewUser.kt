package br.com.clickbook.models
import com.google.firebase.firestore.Exclude

data class NewUser (
    val name: String? = null,
    val email: String? = null,
    @Exclude val password: String? = null
)
