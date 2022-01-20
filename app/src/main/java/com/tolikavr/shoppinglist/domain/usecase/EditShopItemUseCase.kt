package com.tolikavr.shoppinglist.domain.usecase

import com.tolikavr.shoppinglist.domain.model.ShopItem
import com.tolikavr.shoppinglist.domain.repository.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

  suspend fun editShopItem(shopItem: ShopItem){
    shopListRepository.editShopItem(shopItem)
  }
}