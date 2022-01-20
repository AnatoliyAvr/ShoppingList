package com.tolikavr.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.tolikavr.shoppinglist.domain.model.ShopItem

interface ShopListRepository {

  suspend fun addShopItem(shopItem: ShopItem)

  suspend fun deleteShopItem(shopItem: ShopItem)

  suspend fun editShopItem(shopItem: ShopItem)

  suspend fun getShopItem(shopItemId: Int): ShopItem

  fun getShopList(): LiveData<List<ShopItem>>
}