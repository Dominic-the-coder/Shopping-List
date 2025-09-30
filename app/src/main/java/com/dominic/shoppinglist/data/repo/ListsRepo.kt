package com.dominic.shoppinglist.data.repo

import com.dominic.shoppinglist.data.model.List

class ListsRepo private constructor(){
    val map = mutableMapOf<Int, List>()
    var counter = 0

    fun add(list: List) {
        counter++
        map[counter] = list.copy(id = counter)
    }

    fun getListById(id: Int): List? {
        return map[id]
    }

    fun getLists() = map.values.toList()

    fun deleteList(id: Int) {
        map.remove(id)
    }

    fun updateList(id: Int, list: List) {
        map[id] = list
    }

    companion object {
        private var instance: ListsRepo? = null

        fun getInstance(): ListsRepo {
            if (instance == null) {
                instance = ListsRepo()
            }
            return instance!!
        }
    }
}