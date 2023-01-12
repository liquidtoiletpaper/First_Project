package ru.liquidtoiletpaper.myapplication.global

import androidx.compose.runtime.mutableStateListOf
import ru.liquidtoiletpaper.myapplication.screens.Product

object FavId {
    private val _products = mutableStateListOf<Int>()
    val ids: List<Int> = _products
    fun addProducts(id: Int){
        _products.add(id)
    }
    fun removeProducts(id: Int){
        _products.remove(id)
    }
    fun clearProducts(){
        _products.clear()
    }
}