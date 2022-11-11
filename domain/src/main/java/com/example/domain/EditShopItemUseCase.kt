package com.example.domain

import com.example.domain.model.ShopItem
import com.example.domain.repository.ShopListRepository

class EditShopItemUseCase (
    private val repository: ShopListRepository
) {

    fun editShopItem(shopItem: ShopItem) {
        repository.editShopItem(shopItem)
    }
}