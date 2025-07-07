package com.valtech.composeexample.data

data class ProductBasket(
    val id: Int,
    val name: String,
    val price: Double,
    val imageResId: Int,
    var quantity: Int = 0
)
