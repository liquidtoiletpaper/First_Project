package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable

@Serializable
data class RegModel(
    val name: String,
    val lastname: String,
    val email: String,
    val password: String
)