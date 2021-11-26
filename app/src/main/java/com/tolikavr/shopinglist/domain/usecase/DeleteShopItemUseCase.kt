package com.tolikavr.shopinglist.domain.usecase

import com.tolikavr.shopinglist.domain.model.ShopItem
import com.tolikavr.shopinglist.domain.repository.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

  fun deleteShopItem(shopItem: ShopItem){
    shopListRepository.deleteShopItem(shopItem)
  }
}