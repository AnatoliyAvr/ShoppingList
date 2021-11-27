package com.tolikavr.shopinglist.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolikavr.shopinglist.data.repository.ShopListRepositoryImpl
import com.tolikavr.shopinglist.domain.model.ShopItem
import com.tolikavr.shopinglist.domain.usecase.DeleteShopItemUseCase
import com.tolikavr.shopinglist.domain.usecase.EditShopItemUseCase
import com.tolikavr.shopinglist.domain.usecase.GetShopListUseCase

class MainViewModel : ViewModel() {

  private val repository = ShopListRepositoryImpl

  private val getShopListUseCase = GetShopListUseCase(repository)
  private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
  private val editShopItemUseCase = EditShopItemUseCase(repository)

  val shopList = MutableLiveData<List<ShopItem>>()

  fun getShopList() {
    shopList.value = getShopListUseCase.getShopList()
  }

  fun deleteShopItem(shopItem: ShopItem) {
    deleteShopItemUseCase.deleteShopItem(shopItem)
    getShopList()
  }

  fun changeEnableState(shopItem: ShopItem) {
    val newItem = shopItem.copy(enabled = !shopItem.enabled)
    editShopItemUseCase.editShopItem(newItem)
    getShopList()
  }
}