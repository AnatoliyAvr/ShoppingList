package com.example.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ShopItem
import com.example.shoppinglist.R

class ShopListAdapter(
    val onClick: OnClick
) : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList: List<ShopItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemCount() = shopList.size

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.bind(shopItem = shopList[position])
        holder.itemView.setOnClickListener {
            onClick.click(shopItem = shopList[position])
        }
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

interface OnClick {
    fun click(shopItem: ShopItem)
}