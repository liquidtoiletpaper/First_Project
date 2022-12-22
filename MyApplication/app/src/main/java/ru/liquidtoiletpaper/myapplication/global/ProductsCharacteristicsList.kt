package ru.liquidtoiletpaper.myapplication.global

import androidx.compose.runtime.mutableStateListOf
import ru.liquidtoiletpaper.myapplication.characteristics.Characteristics
import ru.liquidtoiletpaper.myapplication.screens.Product

object ProductsCharacteristicsList {
    private val _chars = mutableStateListOf<Characteristics>()
    val products: List<Characteristics> = _chars
    fun addChars(char: Characteristics){
        _chars.add(char)
    }
    fun clearChars(){
        _chars.clear()
    }
}