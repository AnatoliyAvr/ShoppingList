package com.example.domain

import com.example.domain.model.ShopItem
import com.example.domain.repository.ShopListRepository

class DeleteShopItemUseCase (
    private val repository: ShopListRepository
) {

    suspend fun deleteShopItem(shopItem: ShopItem) {
        repository.deleteShopItem(shopItem)
    }
}