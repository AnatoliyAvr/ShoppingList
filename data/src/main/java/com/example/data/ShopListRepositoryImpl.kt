package com.example.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
//import androidx.lifecycle.Transformations
import com.example.data.mapper.ShopListMapper
import com.example.domain.model.ShopItem
import com.example.domain.repository.ShopListRepository

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

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

    override fun getShopList(): LiveData<List<ShopItem>> {
        return MediatorLiveData<List<ShopItem>>().apply {
            addSource(shopListDao.getShopList()) {
                mapper.mapListDbModelToListEntity(it)
            }
        }
    }

//    override fun getShopList(): LiveData<List<ShopItem>> {
//        Transformations.map(shopListDao.getShopList()){
//            mapper.mapListDbModelToListEntity(it)
//        }
//    }
}