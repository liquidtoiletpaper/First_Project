package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ResponseShell(
    val status: String,
    val content: JsonElement? = null,
    val code: Int? = null,
    val description: String? = null
)