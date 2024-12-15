package com.kocerlabs.paparauiclone.ui.home.viewpagerfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kocerlabs.paparauiclone.databinding.FragmentHomePager1Binding
import com.kocerlabs.paparauiclone.databinding.FragmentHomePager5Binding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment

class HomePagerFragment5 : BaseFragment<FragmentHomePager5Binding>(){
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomePager5Binding = FragmentHomePager5Binding.inflate(inflater, container, false)

}