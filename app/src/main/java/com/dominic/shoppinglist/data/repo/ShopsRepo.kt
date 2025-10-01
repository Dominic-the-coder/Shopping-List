package com.dominic.shoppinglist.data.repo

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

    fun generateRandomList(n: Int) {
        repeat(n) {
            val shop = Shop(
                name = "Name $it",
                desc = " Description $it"
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