package com.tolikavr.shoppinglist.domain.usecase

import androidx.lifecycle.LiveData
import com.tolikavr.shoppinglist.domain.model.ShopItem
import com.tolikavr.shoppinglist.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

  fun getShopList(): LiveData<List<ShopItem>> {
    return shopListRepository.getShopList()
  }
}