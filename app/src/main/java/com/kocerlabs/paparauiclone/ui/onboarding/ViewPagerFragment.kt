package com.kocerlabs.viewpager2withnavigation.onboarding

import BoardingFirstFragment
import BoardingFourthFragment
import BoardingSecondFragment
import BoardingThirdFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kocerlabs.paparauiclone.databinding.FragmentViewPagerBinding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment
import com.kocerlabs.paparauiclone.ui.onboarding.BoardingFifthFragment
import com.kocerlabs.paparauiclone.util.goToOtherFragment

class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewPagerBinding = FragmentViewPagerBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPagers()
        initBtnClickers()

    }

    private fun initBtnClickers() {
        with(binding!!) {
            btnLogin.setOnClickListener { goToOtherFragment(ViewPagerFragmentDirections.viewPagerFragmentToLoginFragment()) }
            btnRegister.setOnClickListener { goToOtherFragment(ViewPagerFragmentDirections.viewPagerFragmentToRegisterFragment()) }
        }
    }


    private fun initViewPagers() {
        val fragmentList = listOf<Fragment>(
            BoardingFirstFragment(),
            BoardingSecondFragment(),
            BoardingThirdFragment(),
            BoardingFourthFragment(),
            BoardingFifthFragment(),
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            viewLifecycleOwner.lifecycle
        )
        with(binding!!) {
            viewPager2.adapter = adapter

            dotIndicator.visibility = View.VISIBLE
            dotIndicator.attachTo(viewPager2)
        }
    }


}