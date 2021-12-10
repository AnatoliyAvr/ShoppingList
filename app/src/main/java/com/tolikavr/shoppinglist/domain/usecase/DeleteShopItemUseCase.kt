package com.tolikavr.shoppinglist.domain.usecase

import com.tolikavr.shoppinglist.domain.model.ShopItem
import com.tolikavr.shoppinglist.domain.repository.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

  fun deleteShopItem(shopItem: ShopItem){
    shopListRepository.deleteShopItem(shopItem)
  }
}