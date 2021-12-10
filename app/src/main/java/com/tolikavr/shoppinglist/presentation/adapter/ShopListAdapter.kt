package com.tolikavr.shoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tolikavr.shoppinglist.R
import com.tolikavr.shoppinglist.domain.model.ShopItem


class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

  var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
  var onShopItemClickListener: ((ShopItem) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
    val layout = when (viewType) {
      VIEW_TYPE_ENABLE -> R.layout.item_shop_enabled
      VIEW_TYPE_DISABLE -> R.layout.item_shop_disabled
      else -> throw RuntimeException("Unknown view type: $viewType")
    }
    val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

    return ShopItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
    val shopItem = getItem(position)
    holder.bind(shopItem, {
      onShopItemLongClickListener?.invoke(shopItem)
    }, {
      onShopItemClickListener?.invoke(shopItem)
    })
  }

  override fun getItemViewType(position: Int): Int {
    val item = getItem(position)
    return when (item.enabled) {
      true -> VIEW_TYPE_ENABLE
      false -> VIEW_TYPE_DISABLE
    }
  }

  companion object {
    const val VIEW_TYPE_ENABLE = 100
    const val VIEW_TYPE_DISABLE = 101

    const val MAX_POOL_SIZE = 30
  }
}

