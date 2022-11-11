package com.example.domain

import androidx.lifecycle.LiveData
import com.example.domain.model.ShopItem
import com.example.domain.repository.ShopListRepository

class GetShopListUseCase(
    private val repository: ShopListRepository
) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return repository.getShopList()
    }
}