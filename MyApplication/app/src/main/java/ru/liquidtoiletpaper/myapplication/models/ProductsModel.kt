package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement


@Serializable
data class ProductsModel(
    val content: JsonElement? = null
)