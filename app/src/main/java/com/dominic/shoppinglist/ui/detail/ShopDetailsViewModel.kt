package com.dominic.shoppinglist.ui.detail

import androidx.lifecycle.ViewModel
import com.dominic.shoppinglist.data.enums.Status
import com.dominic.shoppinglist.data.model.Shop
import com.dominic.shoppinglist.data.repo.ShopsRepo
import java.lang.Exception

class ShopDetailsViewModel(
    private val repo: ShopsRepo = ShopsRepo.getInstance()
) : ViewModel() {
    private var shop: Shop? = null

    fun getShop(id: Int): Shop {
        repo.getShopsById(id)?.let {
            shop = it
        }
        return this.shop ?: throw Exception("Shop doesn't exist")
    }

    fun changeStatus(shop: Shop) {
        val newStatus = when (shop.status) {
            Status.INCART -> Status.NOTINCART
            Status.NOTINCART -> Status.INCART
        }
        repo.updateShop(shop.id!!, shop.copy(status = newStatus))
    }

    fun deleteShop(shopId: Int) {
        repo.deleteShop(shopId)
    }
}