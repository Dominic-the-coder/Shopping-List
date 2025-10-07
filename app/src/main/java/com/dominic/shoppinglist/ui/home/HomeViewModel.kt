package com.dominic.shoppinglist.ui.home

import com.dominic.shoppinglist.data.enums.Status
import kotlinx.coroutines.flow.update


class HomeViewModel : BaseHomeViewModel() {
    init {
        getShops()
    }
    override fun getShops() {
        _shops.update {
            repo.getShops().filter { it.status == Status.NOTINCART }
                .filter {
                    currentSearch.isBlank() || it.name.contains(currentSearch, ignoreCase = true)
                }.applySort(currentOrder)
        }
    }
}