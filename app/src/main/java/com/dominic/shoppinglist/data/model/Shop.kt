package com.dominic.shoppinglist.data.model

import com.dominic.shoppinglist.data.enums.Status

data class Shop (
    val name: String,
    val desc: String,
    val quantity: Int = 0,
    val status: Status = Status.NOTINCART,
    val id: Int? = null,
)