package ru.liquidtoiletpaper.myapplication.screens

import android.media.Image
import ru.liquidtoiletpaper.myapplication.R

data class ProductItem (
    val id: String,
    val image: Int,
    val name: String,
    val description: String,
    //val characteristics: MutableList<List<String>>,
    val cost: Int
)