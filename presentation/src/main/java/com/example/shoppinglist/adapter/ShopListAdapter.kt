package com.example.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ShopItem
import com.example.shoppinglist.R

const val VIEW_TYPE_ENABLE = 100
const val VIEW_TYPE_DISABLE = 101
const val MAX_POOL_SIZE = 30

class ShopListAdapter(
    private val onShopItemLongClickListener: OnShopItemLongClickListener
) : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList: List<ShopItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLE ->
                R.layout.item_shop_enabled
            VIEW_TYPE_DISABLE ->
                R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemCount() = shopList.size

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.bind(shopItem = shopList[position])
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener.onShopItemLongClick(shopItem = shopList[position])
            return@setOnLongClickListener true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enabled) VIEW_TYPE_ENABLE else VIEW_TYPE_DISABLE
    }

    class ShopItemViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val tvName = item.findViewById<TextView>(R.id.tv_name)
        private val tvCount = item.findViewById<TextView>(R.id.tv_count)
        fun bind(shopItem: ShopItem) {
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
        }
    }
}

interface OnShopItemLongClickListener {
    fun onShopItemLongClick(shopItem: ShopItem)
}