package com.kocerlabs.paparauiclone.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kocerlabs.paparauiclone.databinding.FragmentWrongPasswordBinding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment
import com.kocerlabs.paparauiclone.util.goToBack

class WrongPasswordFragment : BaseFragment<FragmentWrongPasswordBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWrongPasswordBinding =
        FragmentWrongPasswordBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding!!.btnBack.setOnClickListener {
            goToBack()
        }
    }
}