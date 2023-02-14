package com.example.shoppinglist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.adapter.ShopListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var rvShopList: RecyclerView
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
        rvShopList = findViewById(R.id.rv_shop_list)
        shopListAdapter = ShopListAdapter()
        with(rvShopList) {
            adapter = shopListAdapter
        }

        itemClick()
        itemLongClick()
        touchHelper(rvShopList)
    }

    private fun touchHelper(rv: RecyclerView) {
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteShopItem(shopListAdapter.shopList[viewHolder.adapterPosition])
            }

        }

        val helper = ItemTouchHelper(itemTouchHelper)
        helper.attachToRecyclerView(rv)
    }

    private fun itemLongClick() {
        shopListAdapter.shopItemLongClick = {
            viewModel.changeEnableState(it)
        }
    }

    private fun itemClick() {
        shopListAdapter.shopItemClick = {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
    }
}