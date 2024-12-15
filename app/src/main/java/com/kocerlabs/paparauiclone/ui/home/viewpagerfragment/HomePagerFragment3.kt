package com.kocerlabs.paparauiclone.ui.home.viewpagerfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kocerlabs.paparauiclone.databinding.FragmentHomePager1Binding
import com.kocerlabs.paparauiclone.databinding.FragmentHomePager3Binding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment

class HomePagerFragment3 : BaseFragment<FragmentHomePager3Binding>(){
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomePager3Binding = FragmentHomePager3Binding.inflate(inflater, container, false)
}