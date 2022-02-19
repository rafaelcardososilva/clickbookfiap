package br.com.clickbook.ui.profile

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import br.com.clickbook.R
import br.com.clickbook.models.RequestState
import br.com.clickbook.ui.base.auth.BaseAuthFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment

class ProfileFragment: BaseAuthFragment() {
    private val profileViewModel: ProfileViewModel by viewModels()

    override val layout = R.layout.fragment_profile

    private lateinit var btSignOut: Button
    private lateinit var tvEmailProfile: TextView

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

    private fun setUpView(view: View) {
        btSignOut = view.findViewById(R.id.btSignOut)
        tvEmailProfile = view.findViewById(R.id.tvEmailProfile)

        tvEmailProfile.text = profileViewModel.user?.email
        btSignOut.setOnClickListener {
            profileViewModel.logout()
        }
    }

    private fun registerObserver() {
        this.profileViewModel.loginState.observe(viewLifecycleOwner, {
            when (it) {
                is RequestState.Success -> {
                    hideLoading()
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.logInFragmentNav)
                }
            }
        })
    }
}