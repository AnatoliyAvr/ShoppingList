package com.example.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.ShopItem
import com.example.shoppinglist.R

const val VIEW_TYPE_ENABLE = 100
const val VIEW_TYPE_DISABLE = 101

const val MAX_POOL_SIZE = 30

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemClick: ((ShopItem) -> Unit)? = null
    var onShopItemLongClick: ((ShopItem) -> Unit)? = null

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
        holder.bind(shopItem)
        holder.itemView.setOnClickListener {
            onShopItemClick?.invoke(shopItem)
        }
        holder.itemView.setOnLongClickListener {
            onShopItemLongClick?.invoke(shopItem)
            return@setOnLongClickListener true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) VIEW_TYPE_ENABLE else VIEW_TYPE_DISABLE
    }
}