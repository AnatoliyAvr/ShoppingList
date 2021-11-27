package com.tolikavr.shopinglist.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tolikavr.shopinglist.R
import com.tolikavr.shopinglist.domain.model.ShopItem

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    viewModel.getShopList()
    viewModel.deleteShopItem(ShopItem("Name0", 0, true, 0))
    viewModel.changeEnableState(ShopItem("Name1", 1, true, 1))

    viewModel.shopList.observe(this) {
      Log.d("MainActivityTest", "$it")
    }

  }
}