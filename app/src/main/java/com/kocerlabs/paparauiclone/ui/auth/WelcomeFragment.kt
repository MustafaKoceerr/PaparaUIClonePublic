package com.kocerlabs.paparauiclone.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kocerlabs.paparauiclone.data.database.UserPreferences
import com.kocerlabs.paparauiclone.databinding.FragmentWelcomeBinding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment
import com.kocerlabs.paparauiclone.util.goToOtherFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWelcomeBinding = FragmentWelcomeBinding.inflate(inflater, container, false)

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(800)
            if (userPreferences.authToken.first().isNullOrEmpty()) {
                goToOtherFragment(WelcomeFragmentDirections.welcomeFragmentToViewPagerFragment())
            } else {
                goToOtherFragment(WelcomeFragmentDirections.welcomeFragmentToLoginSavedUserFragment())
            }
        }

    }


}