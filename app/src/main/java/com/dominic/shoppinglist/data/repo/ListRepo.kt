package com.dominic.shoppinglist.data.repo

import com.dominic.shoppinglist.data.model.List

class ListRepo private constructor(){
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
        private var instance: ListRepo? = null

        fun getInstance(): ListRepo {
            if (instance == null) {
                instance = ListRepo()
            }
            return instance!!
        }
    }
}