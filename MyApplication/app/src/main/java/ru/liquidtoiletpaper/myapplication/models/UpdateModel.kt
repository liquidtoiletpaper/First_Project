package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateModel(
    val name: String,
    val lastname: String,
    val gender: String,
    val email: String
)
