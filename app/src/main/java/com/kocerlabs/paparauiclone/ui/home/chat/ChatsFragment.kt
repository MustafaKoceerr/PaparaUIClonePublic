package com.kocerlabs.paparauiclone.ui.home.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocerlabs.paparauiclone.databinding.FragmentChatsBinding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment
import com.kocerlabs.paparauiclone.util.bottomNavigationVisibility
import com.kocerlabs.paparauiclone.util.goToBack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatsFragment : BaseFragment<FragmentChatsBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChatsBinding = FragmentChatsBinding.inflate(inflater, container, false)

    private val viewModel: ChatsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationVisibility(false)

        initChats()
        initSetOnClickers()
    }

    private fun initChats() {
        viewModel.chats.observe(viewLifecycleOwner, Observer { chats ->
            with(binding!!.recyclerChats) {
                adapter = ChatsAdapter(chats)
                layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        })
        viewModel.getChats()
    }

    private fun initSetOnClickers() {
        with(binding!!) {
            imgBackButton.setOnClickListener {
                goToBack()
            }
        }
    }

}