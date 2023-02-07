package com.example.shoppinglist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ShopItem
import com.example.shoppinglist.R
import com.example.shoppinglist.adapter.OnClick
import com.example.shoppinglist.adapter.ShopListAdapter

class MainActivity : AppCompatActivity(), OnClick {

    private lateinit var viewModel: MainViewModel
    private lateinit var rv: RecyclerView
    private lateinit var adapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.rv_shop_list)
        adapter = ShopListAdapter(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        rv.adapter = adapter
        viewModel.shopList.observe(this) {
            adapter.shopList = it
        }
    }

    override fun click(shopItem: ShopItem) {
        viewModel.changeEnableState(shopItem)
    }
}