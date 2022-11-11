package com.example.domain

import com.example.domain.model.ShopItem
import com.example.domain.repository.ShopListRepository

class GetShopListUseCase(
    private val repository: ShopListRepository
) {

    fun getShopList(): List<ShopItem> {
        return repository.getShopList()
    }
}