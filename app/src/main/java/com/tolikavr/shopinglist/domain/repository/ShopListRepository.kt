package com.tolikavr.shopinglist.domain.repository

import com.tolikavr.shopinglist.domain.model.ShopItem

interface ShopListRepository {

  fun addShopItem(shopItem: ShopItem)

  fun deleteShopItem(shopItem: ShopItem)

  fun editShopItem(shopItem: ShopItem)

  fun getShopItem(shopId: Int): ShopItem

  fun getShopList(): List<ShopItem>
}