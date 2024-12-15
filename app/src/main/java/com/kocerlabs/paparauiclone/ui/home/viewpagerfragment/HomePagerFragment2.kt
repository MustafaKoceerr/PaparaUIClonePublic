package com.kocerlabs.paparauiclone.ui.home.viewpagerfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kocerlabs.paparauiclone.databinding.FragmentHomePager2Binding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment

class HomePagerFragment2 : BaseFragment<FragmentHomePager2Binding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomePager2Binding = FragmentHomePager2Binding.inflate(inflater, container, false)

}