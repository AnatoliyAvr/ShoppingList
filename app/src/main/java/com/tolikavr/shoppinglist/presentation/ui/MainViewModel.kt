package com.tolikavr.shoppinglist.presentation.ui

import androidx.lifecycle.ViewModel
import com.tolikavr.shoppinglist.data.repository.ShopListRepositoryImpl
import com.tolikavr.shoppinglist.domain.model.ShopItem
import com.tolikavr.shoppinglist.domain.usecase.DeleteShopItemUseCase
import com.tolikavr.shoppinglist.domain.usecase.EditShopItemUseCase
import com.tolikavr.shoppinglist.domain.usecase.GetShopListUseCase

class MainViewModel : ViewModel() {

  private val repository = ShopListRepositoryImpl

  private val getShopListUseCase = GetShopListUseCase(repository)
  private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
  var editShopItemUseCase = EditShopItemUseCase(repository)

  val shopList = getShopListUseCase.getShopList()

  fun deleteShopItem(shopItem: ShopItem) {
    deleteShopItemUseCase.deleteShopItem(shopItem)
  }

  fun changeEnableState(shopItem: ShopItem) {
    val newItem = shopItem.copy(enabled = !shopItem.enabled)
    editShopItemUseCase.editShopItem(newItem)
  }
}