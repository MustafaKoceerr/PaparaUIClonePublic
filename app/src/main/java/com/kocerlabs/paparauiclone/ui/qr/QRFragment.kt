package com.kocerlabs.paparauiclone.ui.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocerlabs.paparauiclone.data.network.model.MenuModel
import com.kocerlabs.paparauiclone.databinding.FragmentQRBinding
import com.kocerlabs.paparauiclone.ui.base.BottomBaseFragment
import com.kocerlabs.paparauiclone.ui.home.drawermenu.DrawerMenuViewModel
import com.kocerlabs.paparauiclone.ui.home.drawermenu.MenuAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QRFragment : BottomBaseFragment<FragmentQRBinding>() {
    // base fragmentta her şeyi hallettim.
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQRBinding = FragmentQRBinding.inflate(inflater, container, false)

    private val viewModel: DrawerMenuViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetOnClickers(binding!!.root)
        initQRMenu()
    }

    private fun initQRMenu() {
        viewModel.qrMenu.observe(viewLifecycleOwner, Observer { QRMenu ->
            with(binding!!.recyclerLeftMenu) {
                adapter = MenuAdapter(QRMenu, ::getMenuAndNavigate)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        })
        viewModel.getQRMenu()
    }


    private fun getMenuAndNavigate(model: MenuModel) {
        when (model.title) {
            "QR ile Ödeme Yap" -> {
                // Navigate to "QR ile Ödeme Yap" screen
            }

            "QR ile Ödeme Al" -> {
                // Navigate to "QR ile Ödeme Al" screen
            }

            "QR ile ATM'den Para Çek" -> {
                // Navigate to "QR ile ATM'den Para Çek" screen
            }

            else -> {
                // Handle unknown menu item
            }
        }
    }
}