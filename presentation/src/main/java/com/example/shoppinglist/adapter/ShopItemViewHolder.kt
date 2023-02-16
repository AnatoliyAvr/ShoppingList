package com.example.shoppinglist.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ShopItem
import com.example.shoppinglist.R

class ShopItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val tvName = item.findViewById<TextView>(R.id.tv_name)
    private val tvCount = item.findViewById<TextView>(R.id.tv_count)

    fun bind(shopItem: ShopItem) {
        tvName.text = shopItem.name
        tvCount.text = shopItem.count.toString()
    }
}