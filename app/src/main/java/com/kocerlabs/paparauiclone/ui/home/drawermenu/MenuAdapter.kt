package com.kocerlabs.paparauiclone.ui.home.drawermenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.kocerlabs.paparauiclone.data.network.model.MenuModel
import com.kocerlabs.paparauiclone.databinding.ViewholderFragmentIconBinding

class MenuAdapter(val menuList: List<MenuModel>, private val callback: (model: MenuModel) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val binding: ViewholderFragmentIconBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MenuModel) {
            with(binding) {
                txtTitle.text = model.title

                val svgDrawable = AppCompatResources.getDrawable(
                    itemView.context,
                    model.drawableId
                )
                imgIcon.setImageDrawable(svgDrawable)

                itemView.setOnClickListener {
                    callback(model)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder =
        MenuViewHolder(
            ViewholderFragmentIconBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuList[position])
    }


}