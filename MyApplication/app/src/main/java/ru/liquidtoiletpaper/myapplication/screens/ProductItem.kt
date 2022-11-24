package ru.liquidtoiletpaper.myapplication.screens

data class ProductItem (
    val id: String,
    val image: Int,
    val name: String,
    val description: String,
    //val characteristics: MutableList<List<String>>,
    val cost: Int
)