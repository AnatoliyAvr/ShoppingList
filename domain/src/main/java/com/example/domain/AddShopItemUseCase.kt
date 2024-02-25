package com.example.domain

import com.example.domain.model.ShopItem
import com.example.domain.repository.ShopListRepository

class AddShopItemUseCase(
    private val repository: ShopListRepository
) {

    suspend fun addShopItem(shopItem: ShopItem) {
        repository.addShopItem(shopItem)
    }
}