package com.tolikavr.shopinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tolikavr.shopinglist.R
import com.tolikavr.shopinglist.domain.model.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

  var shopList = listOf<ShopItem>()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled, parent, false)
    return ShopItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
    holder.bind(shopList[position])
  }

  override fun getItemCount(): Int = shopList.size

  override fun onViewRecycled(holder: ShopItemViewHolder) {
    super.onViewRecycled(holder)
    holder.tvName.text = ""
    holder.tvCount.text = ""
    holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
  }

  class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvCount = view.findViewById<TextView>(R.id.tv_count)

    fun bind(shopItem: ShopItem) {
      val status = if (shopItem.enabled) "Active" else " Not Active"

      view.setOnLongClickListener {
        true
      }
      if (shopItem.enabled) {
        tvName.text = "${shopItem.name} $status"
        tvCount.text = shopItem.count.toString()
        tvName.setTextColor(ContextCompat.getColor(view.context, android.R.color.holo_red_light))
      }
    }
  }
}