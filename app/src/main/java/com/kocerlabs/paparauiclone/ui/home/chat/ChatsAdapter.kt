package com.kocerlabs.paparauiclone.ui.home.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kocerlabs.paparauiclone.data.network.model.ChatsModel
import com.kocerlabs.paparauiclone.databinding.ViewholderChatsBinding
import com.kocerlabs.paparauiclone.util.formatTransactionDates

class ChatsAdapter(val chatList: List<ChatsModel>) :
    RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder>() {

    inner class ChatsViewHolder(val binding: ViewholderChatsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ChatsModel) {
            with(binding) {
                txtName.text = model.name
                txtLastMessage.text = model.lastMessage

                Glide.with(itemView.context)
                    .load(model.icon)
                    .into(imgIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder =
        ChatsViewHolder(
            ViewholderChatsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = chatList.size

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        holder.bind(chatList[position])
    }


}