package com.kocerlabs.paparauiclone.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kocerlabs.paparauiclone.data.network.model.TransactionModel
import com.kocerlabs.paparauiclone.databinding.ViewholderTransactionsBinding
import com.kocerlabs.paparauiclone.util.colorHelper
import com.kocerlabs.paparauiclone.util.formatTransactionDates

class TransactionAdapter(
    val transactionList: List<TransactionModel>,
    val callback: (model: TransactionModel) -> Unit
) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(val binding: ViewholderTransactionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TransactionModel) {
            with(binding) {
                txtTitle.text = model.title
                txtDescription.text = model.description
                txtDate.text = formatTransactionDates(model.transactionDate)
                txtMoney.text = "₺${model.price}"
                txtMoney.setTextColor(colorHelper(itemView.context, model.isSpending))

                Glide.with(itemView.context)
                    .load(model.drawableUrl)
                    .into(imgIcon)

                itemView.setOnClickListener {
                    callback(model)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder =
        TransactionViewHolder(
            ViewholderTransactionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionList[position])
    }


}