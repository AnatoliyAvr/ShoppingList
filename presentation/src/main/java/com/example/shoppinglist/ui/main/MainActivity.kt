package com.example.shoppinglist.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.adapter.MAX_POOL_SIZE
import com.example.shoppinglist.adapter.ShopListAdapter
import com.example.shoppinglist.adapter.VIEW_TYPE_DISABLE
import com.example.shoppinglist.adapter.VIEW_TYPE_ENABLE
import com.example.shoppinglist.ui.shopItem.ShopItemActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var rvShopList: RecyclerView
    private lateinit var shopListAdapter: ShopListAdapter
    private lateinit var buttonAddShopItem: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycler()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
        buttonAddShopItem = findViewById(R.id.button_add_shop_item)
        buttonAddShopItem.setOnClickListener {
            startActivity(ShopItemActivity.newIntentAddItem(this))
        }
    }

    private fun setupRecycler() {
        rvShopList = findViewById(R.id.rv_shop_list)
        shopListAdapter = ShopListAdapter()
        with(rvShopList) {
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                VIEW_TYPE_ENABLE,
                MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                VIEW_TYPE_DISABLE,
                MAX_POOL_SIZE
            )
        }
        setupClickListener()
        setupLongClickListener()
        setupSwipeListener(rvShopList)
    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemClick = {
            startActivity(ShopItemActivity.newIntentEditItem(this, it.id))
        }
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClick = {
            viewModel.changeEnableState(it)
        }
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteShopItem(shopListAdapter.currentList[viewHolder.adapterPosition])
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }
}