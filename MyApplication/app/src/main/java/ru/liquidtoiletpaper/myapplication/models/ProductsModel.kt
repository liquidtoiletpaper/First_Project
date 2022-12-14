package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable


@Serializable
data class ProductsModel(
    val product: List<ProductModel>
)