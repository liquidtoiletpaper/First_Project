package ru.liquidtoiletpaper.myapplication.global

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import ru.liquidtoiletpaper.myapplication.screens.Product

object CartList {
    private val _products = mutableStateListOf<Product>()
    val products: List<Product> = _products
    fun addProducts(product: Product){
        _products.add(product)
    }
    fun removeProducts(product: Product){
        _products.remove(product)
    }
    fun removeAllProducts(product: Product){
        _products.removeAll(listOf(product))
    }
    fun calculateAmount(id: Int): Int {
        val amount = _products.find {
            it.productId == id
        }
        val mass = amount?.let { mutableListOf(amount.productId) }
        Log.d("MyLog", mass.toString())
        return mass!!.size
    }
    fun calculateValue(): Int {
        var value = 0
        for(i in 0 until _products.size){
            value += _products[i].cost
        }
        return value
    }
    fun clearProducts(){
        _products.clear()
    }
}