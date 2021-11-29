package com.tolikavr.shopinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tolikavr.shopinglist.R
import com.tolikavr.shopinglist.domain.model.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

  val list = listOf<ShopItem>()


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
    val view =
      LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled, parent, false)
    return ShopItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
    holder.bind(list[position])
  }

  override fun getItemCount(): Int = list.size

  class ShopItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
    private val tvCount = itemView.findViewById<TextView>(R.id.tv_count)

    fun bind(shopItem: ShopItem) {
      tvName.text = shopItem.name
      tvCount.text = shopItem.count.toString()

      itemView.setOnLongClickListener {

        true
      }
    }
  }
}