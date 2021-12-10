package com.tolikavr.shoppinglist.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tolikavr.shoppinglist.R
import com.tolikavr.shoppinglist.domain.model.ShopItem

class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
  private val tvName = view.findViewById<TextView>(R.id.tv_name)
  private val tvCount = view.findViewById<TextView>(R.id.tv_count)

  fun bind(shopItem: ShopItem, longClick: () -> Unit, click: () -> Unit) {
    tvName.text = shopItem.name
    tvCount.text = shopItem.count.toString()
    view.setOnLongClickListener {
      longClick()
      true
    }
    view.setOnClickListener {
      click()
    }
  }
}