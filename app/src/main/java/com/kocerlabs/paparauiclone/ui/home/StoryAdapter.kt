package com.kocerlabs.paparauiclone.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kocerlabs.paparauiclone.R
import com.kocerlabs.paparauiclone.data.network.model.StoryModel
import com.kocerlabs.paparauiclone.databinding.ViewholderStoryBinding

class StoryAdapter(val storyList: List<StoryModel>) :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(val binding: ViewholderStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: StoryModel) {
            with(binding) {
                val svgDrawable = AppCompatResources.getDrawable(
                    itemView.context,
                    model.svgId
                )
                imgIcon.setImageDrawable(svgDrawable)

                txtDescription.text = model.description
                if (position == 0) {
                    txtDescription.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.black
                        )
                    )
                    imgIcon.setBackgroundResource(R.drawable.drawable_black_bg_circle) // Burada drawable'ı atıyoruz
                }

                Log.d("StoryAdapter", "Desc: ${model.description}  id: ${model.svgId}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder =
        StoryViewHolder(
            ViewholderStoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = storyList.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(storyList[position])
    }


}