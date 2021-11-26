package com.tolikavr.shopinglist.domain.usecase

import com.tolikavr.shopinglist.domain.model.ShopItem
import com.tolikavr.shopinglist.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

  fun getShopItem(shopId: Int): ShopItem {
    return shopListRepository.getShopItem(shopId)
  }
}