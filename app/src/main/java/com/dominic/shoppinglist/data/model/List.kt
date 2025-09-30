package com.dominic.shoppinglist.data.model

data class List (
    val id: Int? = null,
    val name: String,
    val desc: String,
    val quantity: Int = 0
)