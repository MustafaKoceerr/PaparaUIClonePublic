package com.kocerlabs.paparauiclone.ui.home

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocerlabs.paparauiclone.data.network.model.TransactionModel
import com.kocerlabs.paparauiclone.databinding.FragmentHomeBinding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment
import com.kocerlabs.paparauiclone.ui.home.viewpagerfragment.HomePagerFragment1
import com.kocerlabs.paparauiclone.ui.home.viewpagerfragment.HomePagerFragment2
import com.kocerlabs.paparauiclone.ui.home.viewpagerfragment.HomePagerFragment3
import com.kocerlabs.paparauiclone.ui.home.viewpagerfragment.HomePagerFragment4
import com.kocerlabs.paparauiclone.ui.home.viewpagerfragment.HomePagerFragment5
import com.kocerlabs.paparauiclone.ui.home.viewpagerfragment.HomePagerFragment6
import com.kocerlabs.paparauiclone.util.bottomNavigationVisibility
import com.kocerlabs.paparauiclone.util.goToOtherFragment
import com.kocerlabs.viewpager2withnavigation.onboarding.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCurrentUser()
        // todo, token'im var, token'i header atayarak auth api oluşturdum. Bu auth api ile token gerektiren istekleri atabilirim. viewmodel ve repository'de tanımlamam yeterli.
        bottomNavigationVisibility(true)
        initViewPagers()
        initTransactions()
        initStories()
        initSetOnClickers()
    }

    private fun initSetOnClickers() {
        with(binding!!) {
            imgDrawableMenu.setOnClickListener {
                goToOtherFragment(HomeFragmentDirections.homeFragmentToDrawerMenuFragment())
            }
            imgChat.setOnClickListener {
                goToOtherFragment(HomeFragmentDirections.homeFragmentToChatsFragment())
            }
            txtTransactionsTitle.setOnClickListener {
                goToOtherFragment(HomeFragmentDirections.homeFragmentToAccountTransactionsFragment())
            }
        }
    }

    private fun initViewPagers() {
        val fragmentList = listOf<Fragment>(
            HomePagerFragment1(),
            HomePagerFragment2(),
            HomePagerFragment3(),
            HomePagerFragment4(),
            HomePagerFragment5(),
            HomePagerFragment6(),
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            viewLifecycleOwner.lifecycle
        )

        with(binding!!) {
            viewPager2.offscreenPageLimit = 3 // Birkaç sayfayı önceden yükle
            viewPager2.adapter = adapter
            dotIndicator.visibility = View.VISIBLE
            dotIndicator.attachTo(viewPager2)

            viewPager2.setPageTransformer(CustomPageTransformer())
        }
    }


    private fun initCurrentUser() {
        viewModel.currentUser.observe(viewLifecycleOwner, Observer {
            with(binding!!) {
                txtUserName.text = "${it.firstName} ${it.lastName}"
                val spannable = SpannableString("Papara No: ${it.id}")
                spannable.setSpan(
                    UnderlineSpan(),
                    "Papara No: ".length,
                    spannable.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                txtUserPaparaNo.text = spannable
            }
        })
        viewModel.getCurrentUser()
    }

    private fun initTransactions() {
        viewModel.transactions.observe(viewLifecycleOwner, Observer {
            binding!!.recyclerTransactions.apply {
                adapter = TransactionAdapter(it, ::goToTransactionDetails)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        })

        viewModel.getLastTwoTransactions()
    }

    private fun initStories() {
        viewModel.stories.observe(viewLifecycleOwner, Observer { storyList ->
            binding!!.recyclerStory.apply {
                adapter = StoryAdapter(storyList)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        })
        viewModel.getStories()
    }


    private fun goToTransactionDetails(model: TransactionModel) {
        goToOtherFragment(
            HomeFragmentDirections.homeFragmentToTransactionDetailsFragment(
                model
            )
        )
    }
}