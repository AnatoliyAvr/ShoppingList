package com.tolikavr.shoppinglist.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tolikavr.shoppinglist.data.database.AppDataBase
import com.tolikavr.shoppinglist.data.mapper.ShopListMapper
import com.tolikavr.shoppinglist.domain.model.ShopItem
import com.tolikavr.shoppinglist.domain.repository.ShopListRepository

class ShopListRepositoryImpl(
  application: Application
) : ShopListRepository {

  private val shopListDao = AppDataBase.getInstance(application).shopListDao()
  private val mapper = ShopListMapper()

  override suspend fun addShopItem(shopItem: ShopItem) {
    shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
  }

  override suspend fun deleteShopItem(shopItem: ShopItem) {
    shopListDao.deleteShopItem(shopItem.id)
  }

  override suspend fun editShopItem(shopItem: ShopItem) {
    shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
  }

  override suspend fun getShopItem(shopItemId: Int): ShopItem {
    val dbModel = shopListDao.getShopItem(shopItemId)
    return mapper.mapDbModelToEntity(dbModel)
  }

  override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(shopListDao.getShopList()) {
    mapper.mapListDbModelToListEntity(it)
  }
}