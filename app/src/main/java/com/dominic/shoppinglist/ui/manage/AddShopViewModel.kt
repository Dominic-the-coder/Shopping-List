package com.dominic.shoppinglist.ui.manage

import androidx.lifecycle.viewModelScope
import com.dominic.shoppinglist.data.model.Shop
import kotlinx.coroutines.launch

class AddShopViewModel : BaseManageViewModel() {

    override fun submit(shop: Shop) {
        try {
            require(shop.name.isNotBlank()) { "NO_NAME" }
            require(shop.notes.isNotBlank()) { "NO_NOTES" }

            repo.add(shop)

            viewModelScope.launch {
                _finish.emit(Unit)
            }
        } catch (e: Exception) {
            viewModelScope.launch { _error.emit(e.message.toString()) }
        }
    }
}