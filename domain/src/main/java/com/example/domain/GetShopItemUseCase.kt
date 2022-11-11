package com.example.domain

import com.example.domain.model.ShopItem
import com.example.domain.repository.ShopListRepository

class GetShopItemUseCase(
    private val repository: ShopListRepository
) {

    fun getShopItem(shopItemId: Int): ShopItem {
        return repository.getShopItem(shopItemId)
    }
}