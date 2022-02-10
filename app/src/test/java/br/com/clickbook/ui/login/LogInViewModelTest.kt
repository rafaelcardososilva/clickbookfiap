package br.com.clickbook.ui.login

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class LogInViewModelTest {
    lateinit var loginViewModel: LoginViewModel
    private val firebaseAuth = Mockito.mock(FirebaseAuth::class.java)

    companion object {
        const val EMAIL = "usuario@gmail.com"
        const val PASSWORD = "123456"
    }

    @Mock
    private lateinit var mAuth: FirebaseAuth

    @Before
    fun setup() {

        loginViewModel = LoginViewModel()
    }

    @Test
    fun `Should signIn with correct values` () {
        loginViewModel.signIn(
            EMAIL, PASSWORD
        )

        Mockito.verify(firebaseAuth).signInWithEmailAndPassword(EMAIL, PASSWORD)
    }
}