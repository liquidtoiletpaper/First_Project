package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthModel(
    val id: Int,
    val name: String,
    val gender: String,
    val lastname: String,
    val email: String,
    val location: String
)
