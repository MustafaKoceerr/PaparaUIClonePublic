package com.kocerlabs.paparauiclone.ui.home.accounttransactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocerlabs.paparauiclone.databinding.FragmentAccTypeBinding
import com.kocerlabs.paparauiclone.ui.base.BottomBaseFragment
import com.kocerlabs.paparauiclone.ui.home.drawermenu.DrawerMenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccTypeFragment : BottomBaseFragment<FragmentAccTypeBinding>() {
    // base fragmentta her şeyi hallettim.
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccTypeBinding =
        FragmentAccTypeBinding.inflate(layoutInflater, container, false)

    private val viewModel: DrawerMenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initLeftMenu()
        initSetOnClickers(binding!!.root)

    }

    private fun initLeftMenu() {
        viewModel.accTypeMenu.observe(viewLifecycleOwner, Observer { accTypeMenu ->
            with(binding!!.recyclerAccType) {
                adapter = StringMenuAdapter(accTypeMenu, ::getMenuAndNavigate)
                layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        })
        viewModel.getAccTypeMenu()
    }

    private fun getMenuAndNavigate(title: String) {
        when (title) {
            "Hepsi" -> {
                // Handle the "Hepsi" case
            }

            "Türk Lirası" -> {
                // Handle the "Türk Lirası" case
            }

            "Dolar" -> {
                // Handle the "Dolar" case
            }

            else -> {
                // Handle other cases (if needed)
            }
        }
    }


}