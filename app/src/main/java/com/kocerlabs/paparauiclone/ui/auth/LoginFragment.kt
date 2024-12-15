package com.kocerlabs.paparauiclone.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kocerlabs.paparauiclone.databinding.FragmentLoginBinding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment
import com.kocerlabs.paparauiclone.util.goToBack
import com.kocerlabs.paparauiclone.util.goToOtherFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)

    val TAG = "LoginFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        checkEditText()
        with(binding!!) {

            btnContinue.setOnClickListener(::checkEmailOrPassword)

            btnBack.setOnClickListener { goToBack() }
        }
    }

    private fun checkEditText() {
        with(binding!!) {
            edtEmailOrPhone.addTextChangedListener(object : CustomOnChangedTextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Kullanıcı metni değiştirirken kontrol
                    if (s != null && s.indexOf("@") < s.lastIndexOf(".")) {
                        btnContinue.alpha = 1.0f
                        btnContinue.isEnabled = true
                    } else {
                        btnContinue.alpha = 0.3f
                        btnContinue.isEnabled = false
                    }
                }
            })
        }
    }

    private fun checkEmailOrPassword(view: View) {
        // todo gercek api'da view model ile kontrol yapılabilir.
        goToOtherFragment(LoginFragmentDirections.loginFragmentToPasswordFragment(binding!!.edtEmailOrPhone.text.toString()))
    }
}