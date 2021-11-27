package com.tolikavr.shopinglist.domain.usecase

import androidx.lifecycle.LiveData
import com.tolikavr.shopinglist.domain.model.ShopItem
import com.tolikavr.shopinglist.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

  fun getShopList(): LiveData<List<ShopItem>> {
    return shopListRepository.getShopList()
  }
}