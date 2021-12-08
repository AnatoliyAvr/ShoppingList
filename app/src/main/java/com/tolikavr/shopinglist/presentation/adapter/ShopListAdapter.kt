package com.tolikavr.shopinglist.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tolikavr.shopinglist.R
import com.tolikavr.shopinglist.domain.model.ShopItem


class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

  private var count = 0

  var shopList = listOf<ShopItem>()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
    Log.d("AAA", "${++count}")
    val layout = when (viewType) {
      VIEW_TYPE_ENABLE -> R.layout.item_shop_enabled
      VIEW_TYPE_DISABLE -> R.layout.item_shop_disabled
      else -> throw RuntimeException("Unknown view type: $viewType")
    }
    val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

    return ShopItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
    val shopItem = shopList[position]
    holder.bind(shopItem) {
      onShopItemLongClickListener?.invoke(shopItem)
    }
  }

  override fun getItemCount(): Int = shopList.size

  override fun getItemViewType(position: Int): Int {
    val item = shopList[position]
    return when (item.enabled) {
      true -> VIEW_TYPE_ENABLE
      false -> VIEW_TYPE_DISABLE
    }
  }

  companion object {
    const val VIEW_TYPE_ENABLE = 100
    const val VIEW_TYPE_DISABLE = 101
    const val MAX_POOL_SIZE = 15
  }

  class ShopItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val tvName = view.findViewById<TextView>(R.id.tv_name)
    private val tvCount = view.findViewById<TextView>(R.id.tv_count)

    fun bind(shopItem: ShopItem, block: () -> Unit) {
      tvName.text = shopItem.name
      tvCount.text = shopItem.count.toString()
      view.setOnLongClickListener {
        block()
        true
      }
    }
  }
}

