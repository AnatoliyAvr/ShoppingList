package com.tolikavr.shoppinglist.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.tolikavr.shoppinglist.databinding.ItemShopDisabledBinding
import com.tolikavr.shoppinglist.databinding.ItemShopEnabledBinding
import com.tolikavr.shoppinglist.domain.model.ShopItem

class ShopItemViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

  fun bind(shopItem: ShopItem, longClick: () -> Unit, click: () -> Unit) {
    when (binding) {
      is ItemShopDisabledBinding -> {
        binding.tvName.text = shopItem.name
        binding.tvCount.text = shopItem.count.toString()
      }

      is ItemShopEnabledBinding -> {
        binding.tvName.text = shopItem.name
        binding.tvCount.text = shopItem.count.toString()
      }
    }
    binding.root.setOnLongClickListener {
      longClick()
      true
    }
    binding.root.setOnClickListener {
      click()
    }
  }
}