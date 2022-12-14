package ru.liquidtoiletpaper.myapplication.global

import androidx.compose.runtime.mutableStateListOf
import ru.liquidtoiletpaper.myapplication.screens.Product

object FilteredProductsList {
    private val _products = mutableStateListOf<Product>()
    val products: List<Product> = _products
    fun addProducts(product: Product){
        _products.add(product)
    }
    fun clearProducts(){
        _products.clear()
    }
}