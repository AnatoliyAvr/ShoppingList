package com.example.data.mapper

import com.example.data.model.ShopItemDbModel
import com.example.domain.model.ShopItem

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel {
        return ShopItemDbModel(
            id = shopItem.id,
            name = shopItem.name,
            count = shopItem.count,
            enabled = shopItem.enabled
        )
    }

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem {
        return ShopItem(
            id = shopItemDbModel.id,
            name = shopItemDbModel.name,
            count = shopItemDbModel.count,
            enabled = shopItemDbModel.enabled
        )
    }

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>): List<ShopItem> {
        return list.map {
            mapDbModelToEntity(it)
        }
    }
}