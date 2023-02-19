package com.example.shoppinglist.ui.shopItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.ShopListRepositoryImpl
import com.example.domain.AddShopItemUseCase
import com.example.domain.EditShopItemUseCase
import com.example.domain.GetShopItemUseCase
import com.example.domain.model.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    private val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    private val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(shopItemId: Int) {
        _shopItem.value = getShopItemUseCase.getShopItem(shopItemId = shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parsName(inputName)
        val count = parsCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(
                name = name,
                count = count,
                enabled = false
            )
            addShopItemUseCase.addShopItem(ShopItem(name = name, count = count, enabled = true))
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parsName(inputName)
        val count = parsCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun parsName(name: String?): String {
        return name?.trim() ?: ""
    }

    private fun parsCount(count: String?): Int {
        return try {
            count?.trim()?.toInt() ?: 0
        } catch (_: Exception) {
            0
        }
//        return if (count?.isDigitsOnly() != null) count.toInt() else 0
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}