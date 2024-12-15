package com.kocerlabs.paparauiclone.ui.home.viewpagerfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kocerlabs.paparauiclone.databinding.FragmentHomePager1Binding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment

class HomePagerFragment1 : BaseFragment<FragmentHomePager1Binding>(){
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomePager1Binding = FragmentHomePager1Binding.inflate(inflater, container, false)

}