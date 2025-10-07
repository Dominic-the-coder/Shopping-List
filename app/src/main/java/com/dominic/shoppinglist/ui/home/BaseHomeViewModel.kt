package com.dominic.shoppinglist.ui.home

import androidx.lifecycle.ViewModel
import com.dominic.shoppinglist.data.enums.SortBy
import com.dominic.shoppinglist.data.model.Shop
import com.dominic.shoppinglist.data.repo.ShopsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseHomeViewModel(
    protected val repo: ShopsRepo = ShopsRepo.getInstance()
) : ViewModel() {
    protected val _shops = MutableStateFlow<List<Shop>>(emptyList())
    val shops: StateFlow<List<Shop>> = _shops
    protected var currentOrder = SortBy.AZ
    protected var currentSearch = ""

    abstract fun getShops()

    fun refresh() {
        getShops()
    }

    fun setSearch(string: String) {
        currentSearch = string
        getShops()
    }

    fun setSorting(sortBy: SortBy) {
        currentOrder = sortBy
        getShops()
    }

    fun List<Shop>.applySort(sortBy: SortBy): List<Shop> {
        return when (sortBy) {
            SortBy.AZ -> this.sortedBy { it.name.lowercase() }
            SortBy.ZA -> this.sortedByDescending { it.name.lowercase() }
        }
    }
}