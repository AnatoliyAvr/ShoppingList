package com.tolikavr.shopinglist.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.tolikavr.shopinglist.R
import com.tolikavr.shopinglist.presentation.adapter.ShopListAdapter

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: MainViewModel
  private lateinit var shopListAdapter: ShopListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupRecyclerView()

    viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    viewModel.shopList.observe(this) {
      shopListAdapter.shopList = it
    }
  }

  private fun setupRecyclerView() {
    val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
    shopListAdapter = ShopListAdapter()
    with(rvShopList) {
      adapter = shopListAdapter
      recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE_ENABLE, ShopListAdapter.MAX_POOL_SIZE)
      recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE_DISABLE, ShopListAdapter.MAX_POOL_SIZE)
    }
  }
}