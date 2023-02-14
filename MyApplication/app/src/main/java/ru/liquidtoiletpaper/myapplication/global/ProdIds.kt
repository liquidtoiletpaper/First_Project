package ru.liquidtoiletpaper.myapplication.global

object ProdIds {
    val products = mutableMapOf<Int, Int>()
    fun addProducts(id: Int){
        products[id] = 1
    }
    fun removeProducts(id: Int){
        products[id] = 0
    }
    fun calculateAmount(id: Int): Int {
        return products[id]!!
    }
    fun amplify(id: Int) {
        products[id] = products[id] !!+ 1
    }
    fun reduce(id: Int) {
        products[id] = products[id]!! - 1
    }
    fun calculateValue(id: Int): Int {
        val value =  ProductsList.products.find {
            it.productId == id
        }
        return value!!.cost * products.get(id)!!
    }
    fun clearProducts(){
        products.clear()
    }
}