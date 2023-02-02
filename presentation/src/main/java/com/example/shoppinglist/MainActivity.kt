package com.example.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llShopItem: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llShopItem = findViewById(R.id.ll_shop_item)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.shopList.observe(this) {
            shopView(it)
        }
    }

    private fun shopView(list: List<ShopItem>) {
        llShopItem.removeAllViews()
        list.forEach { shopItem ->
            val layoutId = if (shopItem.enabled) R.layout.item_shop_enabled else R.layout.item_shop_disabled
            val view = LayoutInflater.from(this).inflate(layoutId, llShopItem, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeEnableState(shopItem)
                return@setOnLongClickListener true
            }
            llShopItem.addView(view)
        }
    }
}