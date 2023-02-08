package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject


@Serializable
data class FavProductsModel(
    val product: JsonArray
)