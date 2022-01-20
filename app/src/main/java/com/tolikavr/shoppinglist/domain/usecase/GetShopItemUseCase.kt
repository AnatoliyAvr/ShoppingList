package com.tolikavr.shoppinglist.domain.usecase

import com.tolikavr.shoppinglist.domain.model.ShopItem
import com.tolikavr.shoppinglist.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

  suspend fun getShopItem(shopId: Int): ShopItem {
    return shopListRepository.getShopItem(shopId)
  }
}