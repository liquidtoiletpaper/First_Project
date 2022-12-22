package ru.liquidtoiletpaper.myapplication.models

import kotlinx.serialization.Serializable

@Serializable
data class CharModel(
    val product_id: Int,
    val country: String? = null,
    val warranty: Int? = null,
    val model: String? = null,
    val brand: String? = null,
    val length: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val gpu_graphicsController: String? = null,
    val gpu_memorySize: Int? = null,
    val gpu_memoryType: String? = null,
    val cpu_socket: String? = null,
    val cpu_coreAmount: Int? = null,
    val cpu_frequency: Double? = null,
    val cpu_consumption: Int? = null,
    val ps_power: Int? = null
)