package com.kocerlabs.paparauiclone.ui.home.drawermenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocerlabs.paparauiclone.data.network.model.MenuModel
import com.kocerlabs.paparauiclone.databinding.FragmentDrawerMenuBinding
import com.kocerlabs.paparauiclone.ui.base.BottomBaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DrawerMenuFragment : BottomBaseFragment<FragmentDrawerMenuBinding>() {
    // base fragmentta her şeyi hallettim.
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDrawerMenuBinding =
        FragmentDrawerMenuBinding.inflate(layoutInflater, container, false)

    private val viewModel: DrawerMenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initLeftMenu()
        initSetOnClickers(binding!!.root)

    }

    private fun initLeftMenu() {
        viewModel.leftMenu.observe(viewLifecycleOwner, Observer { leftMenu ->
            with(binding!!.recyclerLeftMenu) {
                adapter = MenuAdapter(leftMenu, ::getMenuAndNavigate)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        })
        viewModel.getLeftMenu()
    }

    private fun getMenuAndNavigate(model: MenuModel) {
        when (model.title) {
            "Bildirimler" -> { /* Navigate to Notifications */
            }

            "ATM Bul" -> { /* Navigate to Find ATM */
            }

            "Para Çek / Yatır" -> { /* Navigate to Withdraw/Deposit */
            }

            "Harcama Bölüştür" -> { /* Navigate to Expense Split */
            }

            "Papara Edu" -> { /* Navigate to Papara Edu */
            }

            "Cashback" -> { /* Navigate to Cashback */
            }

            "Yayıncı Hesabı" -> { /* Navigate to Streamer Account */
            }

            "Aylık Özet" -> { /* Navigate to Monthly Summary */
            }

            "Ücretler & Limitler" -> { /* Navigate to Fees & Limits */
            }

            "Ticari hesap" -> { /* Navigate to Commercial Account */
            }

            "Sıkça Sorulan Sorular" -> { /* Navigate to FAQs */
            }

            "Destek Merkezi" -> { /* Navigate to Support Center */
            }

            else -> { /* Handle unknown case */
            }
        }

    }


}