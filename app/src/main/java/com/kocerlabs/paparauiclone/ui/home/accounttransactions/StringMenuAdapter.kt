package com.kocerlabs.paparauiclone.ui.home.accounttransactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kocerlabs.paparauiclone.databinding.ViewholderJustStringBinding

class StringMenuAdapter(val stringMenuList: List<String>, private val callback: (str: String) -> Unit) :
    RecyclerView.Adapter<StringMenuAdapter.StringMenuViewHolder>() {

    inner class StringMenuViewHolder(val binding: ViewholderJustStringBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            with(binding) {
                txtTitle.text = title

                itemView.setOnClickListener {
                    callback(title)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringMenuViewHolder =
        StringMenuViewHolder(
            ViewholderJustStringBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = stringMenuList.size

    override fun onBindViewHolder(holder: StringMenuViewHolder, position: Int) {
        holder.bind(stringMenuList[position])
    }


}