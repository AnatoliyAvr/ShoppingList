package com.tolikavr.shopinglist.domain.usecase

import com.tolikavr.shopinglist.domain.model.ShopItem
import com.tolikavr.shopinglist.domain.repository.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

  fun addShopItem(shopItem: ShopItem) {
    shopListRepository.addShopItem(shopItem)
  }
}