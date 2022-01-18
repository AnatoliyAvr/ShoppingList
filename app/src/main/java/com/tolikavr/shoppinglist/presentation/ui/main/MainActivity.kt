package com.tolikavr.shoppinglist.presentation.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.tolikavr.shoppinglist.R
import com.tolikavr.shoppinglist.databinding.ActivityMainBinding
import com.tolikavr.shoppinglist.presentation.adapter.ShopListAdapter
import com.tolikavr.shoppinglist.presentation.ui.shopitem.ShopItemActivity
import com.tolikavr.shoppinglist.presentation.ui.shopitem.ShopItemFragment

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainViewModel
  private lateinit var shopListAdapter: ShopListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setupRecyclerView()

    viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    viewModel.shopList.observe(this) {
      shopListAdapter.submitList(it)
    }

    binding.buttonAddShopItem.setOnClickListener {
      if (isOnePaneMode()) {
        val intent = ShopItemActivity.newIntentAddItem(this)
        startActivity(intent)
      } else {
        launchFragment(ShopItemFragment.newInstanceAddItem())
      }
    }
  }

  override fun onEditingFinished() {
    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    supportFragmentManager.popBackStack()
  }

  private fun isOnePaneMode(): Boolean {
    return binding.shopItemContainer == null
  }

  private fun launchFragment(fragment: Fragment) {
    supportFragmentManager.popBackStack()
    supportFragmentManager.beginTransaction()
      .replace(R.id.shop_item_container, fragment)
      .addToBackStack(null)
      .commit()
  }

  private fun setupRecyclerView() {
    shopListAdapter = ShopListAdapter()
    with(binding.rvShopList) {
      adapter = shopListAdapter
      recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE_ENABLE, ShopListAdapter.MAX_POOL_SIZE)
      recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE_DISABLE, ShopListAdapter.MAX_POOL_SIZE)
    }
    setupLongClickListener()
    setupClickListener()
    setupSwipeListener(binding.rvShopList)
  }

  private fun setupSwipeListener(rvShopList: RecyclerView) {
    val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
      override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
      ): Boolean {
        return false
      }

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val item = shopListAdapter.currentList[viewHolder.adapterPosition]
        viewModel.deleteShopItem(item)
      }
    }

    val itemTouchHelper = ItemTouchHelper(callback)
    itemTouchHelper.attachToRecyclerView(rvShopList)
  }

  private fun setupClickListener() {
    shopListAdapter.onShopItemClickListener = { shopItem ->
      if (isOnePaneMode()) {
        val intent = ShopItemActivity.newIntentEditItem(this, shopItem.id)
        startActivity(intent)
      } else {
        launchFragment(ShopItemFragment.newInstanceEditItem(shopItem.id))
      }
    }
  }

  private fun setupLongClickListener() {
    shopListAdapter.onShopItemLongClickListener = {
      viewModel.changeEnableState(it)
    }
  }

}