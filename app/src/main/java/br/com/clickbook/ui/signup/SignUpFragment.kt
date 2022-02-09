package br.com.clickbook.ui.signup

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.clickbook.R
import br.com.clickbook.extensions.hideKeyboard
import br.com.clickbook.models.NewUser
import br.com.clickbook.models.RequestState
import br.com.clickbook.ui.base.BaseFragment
import br.com.clickbook.ui.base.auth.NAVIGATION_KEY

class SignUpFragment : BaseFragment() {
    override val layout = R.layout.fragment_sign_up

    private val signUpViewModel: SignUpViewModel by viewModels()

    private lateinit var etNameSignUp: EditText
    private lateinit var etEmailSignUp: EditText
    private lateinit var etPasswordSignUp: EditText
    private lateinit var etPasswordAgainSignUp: EditText
    private lateinit var btSignUp: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)

        registerObserver()
    }

    private fun registerObserver() {
        this.signUpViewModel.signUpState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> {
                    showSuccess()
                }
                is RequestState.Error -> {
                    hideLoading()
                    showMessage(it.throwable.message)
                }
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

    private fun setUpView(view: View) {
        etNameSignUp = view.findViewById(R.id.etNameSignUp)
        etEmailSignUp = view.findViewById(R.id.etEmailSignUp)
        etPasswordSignUp = view.findViewById(R.id.etPasswordSignUp)
        etPasswordAgainSignUp = view.findViewById(R.id.etPasswordAgainSignUp)
        btSignUp = view.findViewById(R.id.btSignUp)
        setUpListener()
    }

    private fun setUpListener() {
        btSignUp.setOnClickListener {
            hideKeyboard()

            val name = etNameSignUp.text.toString()
            val email = etEmailSignUp.text.toString()
            val password = etPasswordSignUp.text.toString()
            val passwordAgain = etPasswordAgainSignUp.text.toString()

            if (!password.isNullOrEmpty() && password == passwordAgain) {
                val newUser = NewUser(
                    name, email, password
                )
                signUpViewModel.signUp(newUser)
            } else {
                showMessage("As senhas precisam ser iguais!")
            }
        }
    }
}