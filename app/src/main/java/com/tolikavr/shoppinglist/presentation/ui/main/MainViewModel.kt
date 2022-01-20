package com.tolikavr.shoppinglist.presentation.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tolikavr.shoppinglist.data.repository.ShopListRepositoryImpl
import com.tolikavr.shoppinglist.domain.model.ShopItem
import com.tolikavr.shoppinglist.domain.usecase.DeleteShopItemUseCase
import com.tolikavr.shoppinglist.domain.usecase.EditShopItemUseCase
import com.tolikavr.shoppinglist.domain.usecase.GetShopListUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

  private val repository = ShopListRepositoryImpl(application)

  private val getShopListUseCase = GetShopListUseCase(repository)
  private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
  private var editShopItemUseCase = EditShopItemUseCase(repository)

  val shopList = getShopListUseCase.getShopList()

  fun deleteShopItem(shopItem: ShopItem) {
    deleteShopItemUseCase.deleteShopItem(shopItem)
  }

  fun changeEnableState(shopItem: ShopItem) {
    val newItem = shopItem.copy(enabled = !shopItem.enabled)
    editShopItemUseCase.editShopItem(newItem)
  }
}