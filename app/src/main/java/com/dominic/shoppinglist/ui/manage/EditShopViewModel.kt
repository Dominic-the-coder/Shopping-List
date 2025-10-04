package com.dominic.shoppinglist.ui.manage

import androidx.lifecycle.viewModelScope
import com.dominic.shoppinglist.data.model.Shop
import kotlinx.coroutines.launch

class EditShopViewModel : BaseManageViewModel() {
    private var shop: Shop? = null

    // Gets shop to populate the EditShop's EditText fields
    fun getShop(id: Int): Shop {
        repo.getShopsById(id)?.let {
            shop = it
        }
        return this.shop ?: throw Exception("Shop doesn't exist")
    }

    // Submits to repo.
    override fun submit(newShop: Shop) {
        try {
            require(newShop.name.isNotBlank()) { "NO_NAME" }
            require(newShop.desc.isNotBlank()) { "NO_DESCRIPTION" }

            shop?.let {
                repo.updateShop(
                    it.id!!,
                    it.copy(
                        name = newShop.name,
                        desc = newShop.desc,
                        quantity = newShop.quantity
                    )
                )
            }

            viewModelScope.launch {
                _finish.emit(Unit)
            }
        } catch (e: Exception) {
            viewModelScope.launch { _error.emit(e.message.toString()) }
        }
    }
}