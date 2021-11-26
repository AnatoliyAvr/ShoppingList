package com.tolikavr.shopinglist.domain.usecase

import com.tolikavr.shopinglist.domain.model.ShopItem
import com.tolikavr.shopinglist.domain.repository.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

  fun editShopItem(shopItem: ShopItem){
    shopListRepository.editShopItem(shopItem)
  }
}