package com.tolikavr.shoppinglist.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.tolikavr.shoppinglist.databinding.ItemShopDisabledBinding
import com.tolikavr.shoppinglist.domain.model.ShopItem

class ShopItemViewHolder(private val binding: ItemShopDisabledBinding) : RecyclerView.ViewHolder(binding.root) {

  fun bind(shopItem: ShopItem, longClick: () -> Unit, click: () -> Unit) {
    binding.tvName.text = shopItem.name
    binding.tvCount.text = shopItem.count.toString()
    binding.root.setOnLongClickListener {
      longClick()
      true
    }
    binding.root.setOnClickListener {
      click()
    }
  }
}