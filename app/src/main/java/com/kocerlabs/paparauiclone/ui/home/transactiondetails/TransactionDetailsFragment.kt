package com.kocerlabs.paparauiclone.ui.home.transactiondetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kocerlabs.paparauiclone.databinding.FragmentTransactionDetailsBinding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment
import com.kocerlabs.paparauiclone.util.bottomNavigationVisibility
import com.kocerlabs.paparauiclone.util.colorHelper
import com.kocerlabs.paparauiclone.util.formatTransactionDates
import com.kocerlabs.paparauiclone.util.goToBack

class TransactionDetailsFragment : BaseFragment<FragmentTransactionDetailsBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTransactionDetailsBinding =
        FragmentTransactionDetailsBinding.inflate(inflater, container, false)

    private val args: TransactionDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        bottomNavigationVisibility(false)
        initSetOnClickers()
        bindTransactionDetailsToView()
    }

    private fun bindTransactionDetailsToView() {
        val model = args.transactionModel
        with(binding!!) {
            txtDate.text = formatTransactionDates(model.transactionDate)
            txtTitle.text = model.title
            txtPrice.text = "₺${model.price}"
            txtPrice.setTextColor(colorHelper(requireContext(), model.isSpending))
            txtDescription.text = model.description

            Glide.with(requireContext())
                .load(model.drawableUrl)
                .into(imgIcon)
        }
    }


    private fun initSetOnClickers() {
        with(binding!!) {
            imgBackButton.setOnClickListener {
                goToBack()
            }
        }
    }
}