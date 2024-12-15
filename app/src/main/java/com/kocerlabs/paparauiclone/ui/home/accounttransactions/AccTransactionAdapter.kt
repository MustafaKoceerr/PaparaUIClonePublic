package com.kocerlabs.paparauiclone.ui.home.accounttransactions

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kocerlabs.paparauiclone.data.network.model.TransactionModel
import com.kocerlabs.paparauiclone.databinding.ViewholderAccTransactionBinding
import com.kocerlabs.paparauiclone.util.colorHelper
import com.kocerlabs.paparauiclone.util.formatAndParseTransactionDates

class AccTransactionAdapter(
    val transactionList: List<TransactionModel>,
    val callback: (model: TransactionModel) -> Unit
) :
    RecyclerView.Adapter<AccTransactionAdapter.AccTransactionViewHolder>() {
    val TAG = "AccTransactionAdapter"

    val dateSet = mutableSetOf<String>()

    inner class AccTransactionViewHolder(val binding: ViewholderAccTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TransactionModel) {
            with(binding) {
                txtTitle.text = model.title
                txtDescription.text = model.description

                val date = formatAndParseTransactionDates(model.transactionDate)
                Log.d(TAG, "date $date for ${model.title} ${model.price}")
                if (date[0] !in dateSet) {
                    dateSet.add(date[0])
                    txtDate.text = date[0]
                    txtHour.text = date[1]
                } else {
                    txtDate.visibility = View.GONE
                    txtHour.text = date[1]
                }

                if (!model.extraDesc.isNullOrEmpty()) {
                    txtExtraDescription.visibility = View.VISIBLE
                    txtExtraDescription.text = model.extraDesc
                }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccTransactionViewHolder =
        AccTransactionViewHolder(
            ViewholderAccTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = transactionList.size

    override fun onBindViewHolder(holder: AccTransactionViewHolder, position: Int) {
        holder.bind(transactionList[position])
    }


}