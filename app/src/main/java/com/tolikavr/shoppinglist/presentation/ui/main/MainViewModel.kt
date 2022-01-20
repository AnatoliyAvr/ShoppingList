package com.tolikavr.shoppinglist.presentation.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tolikavr.shoppinglist.data.repository.ShopListRepositoryImpl
import com.tolikavr.shoppinglist.domain.model.ShopItem
import com.tolikavr.shoppinglist.domain.usecase.DeleteShopItemUseCase
import com.tolikavr.shoppinglist.domain.usecase.EditShopItemUseCase
import com.tolikavr.shoppinglist.domain.usecase.GetShopListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

  private val repository = ShopListRepositoryImpl(application)

  private val getShopListUseCase = GetShopListUseCase(repository)
  private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
  private var editShopItemUseCase = EditShopItemUseCase(repository)

  val shopList = getShopListUseCase.getShopList()

  fun deleteShopItem(shopItem: ShopItem) {
    viewModelScope.launch {
      deleteShopItemUseCase.deleteShopItem(shopItem)
    }
  }

  fun changeEnableState(shopItem: ShopItem) {
    viewModelScope.launch {
      val newItem = shopItem.copy(enabled = !shopItem.enabled)
      editShopItemUseCase.editShopItem(newItem)
    }
  }
}