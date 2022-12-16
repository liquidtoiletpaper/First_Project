package ru.liquidtoiletpaper.myapplication.models

import ru.liquidtoiletpaper.myapplication.screens.Product

data class ProductSearchModelState(
    val searchText: String = "",
    val products: List<Product> = arrayListOf(),
    val progressBar: Boolean = false
) {
    companion object {
        val Empty = ProductSearchModelState()
    }

}

