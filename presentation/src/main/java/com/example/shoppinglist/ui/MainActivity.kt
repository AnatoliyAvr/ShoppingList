package com.example.shoppinglist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ShopItem
import com.example.shoppinglist.R
import com.example.shoppinglist.adapter.*

class MainActivity : AppCompatActivity(), OnShopItemLongClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var rv: RecyclerView
    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycler()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.shopList = it
        }
    }

    private fun setupRecycler() {
        rv = findViewById(R.id.rv_shop_list)
        with(rv) {
            shopListAdapter = ShopListAdapter(this@MainActivity)
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_ENABLE, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_DISABLE, MAX_POOL_SIZE)
        }
        setupItemClickListener()
        setupSwipeListener(rv)
    }

    private fun setupItemClickListener() {
        shopListAdapter.onShopItemClickListener = { shopItem ->
            Toast.makeText(this@MainActivity, "${shopItem.id}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupSwipeListener(rv: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.shopList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    override fun onShopItemLongClick(shopItem: ShopItem) {
        viewModel.changeEnableState(shopItem)
    }
}