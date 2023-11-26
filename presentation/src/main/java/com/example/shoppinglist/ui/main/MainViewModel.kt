package com.example.shoppinglist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.data.ShopListRepositoryImpl
import com.example.domain.DeleteShopItemUseCase
import com.example.domain.EditShopItemUseCase
import com.example.domain.GetShopListUseCase
import com.example.domain.model.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        editShopItemUseCase.editShopItem(shopItem.copy(enabled = !shopItem.enabled))
    }
}