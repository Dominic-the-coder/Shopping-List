package com.dominic.shoppinglist.data.repo

import com.dominic.shoppinglist.data.enums.Status
import com.dominic.shoppinglist.data.model.Shop

class ShopsRepo private constructor(){
    val map = mutableMapOf<Int, Shop>()
    var counter = 0

    init {
        generateRandomList(10)
    }

    fun add(shop: Shop) {
        counter++
        map[counter] = shop.copy(id = counter)
    }

    fun getShopsById(id: Int): Shop? {
        return map[id]
    }

    fun getShops() = map.values.toList()

    fun deleteShop(id: Int) {
        map.remove(id)
    }

    fun updateShop(id: Int, shop: Shop) {
        map[id] = shop
    }
    fun clearCompletedShops() {
        val toRemove = map.filterValues { it.status == Status.INCART }.keys
        toRemove.forEach { map.remove(it) }
    }


    fun generateRandomList(n: Int) {
        repeat(n) {
            val shop = Shop(
                name = "Name $it",
                notes = " Note $it",
                category = "Category $it"
            )
            add(shop)
        }
    }

    companion object {
        private var instance: ShopsRepo? = null

        fun getInstance(): ShopsRepo {
            if (instance == null) {
                instance = ShopsRepo()
            }
            return instance!!
        }
    }
}