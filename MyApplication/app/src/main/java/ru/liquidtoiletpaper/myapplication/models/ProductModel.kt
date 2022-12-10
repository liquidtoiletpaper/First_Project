package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    val product_id: Int,
    val image: String,
    val name: String,
    val description: String,
    val category: String,
    val cost: Int
)