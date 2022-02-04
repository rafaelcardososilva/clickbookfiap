package br.com.clickbook.ui.login

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.clickbook.R
import br.com.clickbook.exceptions.EmailInvalidException
import br.com.clickbook.exceptions.PasswordInvalidException
import br.com.clickbook.models.RequestState
import br.com.clickbook.ui.base.BaseFragment
import br.com.clickbook.ui.base.auth.NAVIGATION_KEY
import br.com.heiderlopes.calculaflex.ui.login.LoginViewModel

class LogInFragment: BaseFragment() {
    override val layout = R.layout.fragment_log_in

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var btLogin: Button
    private lateinit var etEmailLogin: EditText
    private lateinit var etPasswordLogin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater
            .from(context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
        registerObserver()
    }

    private fun registerObserver() {
        this.loginViewModel.loginState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> showSuccess()
                is RequestState.Error -> showError(it.throwable)
                is RequestState.Loading -> showLoading()
            }
        })
    }

    private fun showSuccess() {
        hideLoading()
        val navIdFromArguments = arguments?.getInt(NAVIGATION_KEY)
        if (navIdFromArguments == null) {
            findNavController().navigate(R.id.main_nav)
        } else {
            findNavController().popBackStack(navIdFromArguments, false)
        }
    }

    private fun showError(t: Throwable) {
        hideLoading()
        etEmailLogin.error = null;
        etPasswordLogin.error = null

        showMessage(t.message)
        when(t) {
            is EmailInvalidException -> {
                etEmailLogin.error = t.message
                etEmailLogin.requestFocus()
            }
            is PasswordInvalidException -> {
                etPasswordLogin.error = t.message
                etPasswordLogin.requestFocus()
            }
        }
    }

    private fun setUpView(view: View) {
        btLogin = view.findViewById(R.id.btLogin)
        etEmailLogin = view.findViewById(R.id.etEmailLogin)
        etPasswordLogin = view.findViewById(R.id.etPasswordLogin)

        btLogin.setOnClickListener {
            loginViewModel.signIn(
                etEmailLogin.text.toString(),
                etPasswordLogin.text.toString()
            )
        }
    }
}