package com.tolikavr.shoppinglist.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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

  override fun addShopItem(shopItem: ShopItem) {
    shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
  }

  override fun deleteShopItem(shopItem: ShopItem) {
    shopListDao.deleteShopItem(shopItem.id)
  }

  override fun editShopItem(shopItem: ShopItem) {
    shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
  }

  override fun getShopItem(shopItemId: Int): ShopItem {
    val dbModel = shopListDao.getShopItem(shopItemId)
    return mapper.mapDbModelToEntity(dbModel)
  }

//  override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
//    addSource(shopListDao.getShopList()) {
//      value = mapper.mapListDbModelToListEntity(it)
//    }
//  }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(shopListDao.getShopList()) {
    mapper.mapListDbModelToListEntity(it)
  }
}