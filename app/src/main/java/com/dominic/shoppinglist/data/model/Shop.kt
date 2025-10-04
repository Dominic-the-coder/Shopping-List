package com.dominic.shoppinglist.data.model

data class Shop (
    val name: String,
    val desc: String,
    val quantity: Int? = 0,
    val id: Int? = null,
)