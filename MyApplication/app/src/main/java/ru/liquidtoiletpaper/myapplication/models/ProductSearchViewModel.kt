package ru.liquidtoiletpaper.myapplication.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import ru.liquidtoiletpaper.myapplication.global.FilteredProductsList
import ru.liquidtoiletpaper.myapplication.global.ProductsList
import ru.liquidtoiletpaper.myapplication.screens.Product

class ProductSearchViewModel {
    private var allProducts: ArrayList<Product> = ArrayList<Product>()
    private val searchText: MutableStateFlow<String> = MutableStateFlow("")
    private var showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var matchedProducts: MutableStateFlow<List<Product>> = MutableStateFlow(arrayListOf())

    val productSearchModelState = combine(
        searchText,
        matchedProducts,
        showProgressBar
    ) {
            text, matchedUsers, showProgress ->

        ProductSearchModelState(
            text,
            matchedUsers,
            showProgress
        )
    }

    init {
        retrieveProducts()
    }

    fun retrieveProducts() {
        val products = ProductsList.products

        if (products != null) {
            allProducts.addAll(products)
        }
    }

    fun onSearchTextChanged(changedSearchText: String) {
        searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
            matchedProducts.value = arrayListOf()
            return
        }
        val productsFromSearch = allProducts.filter { x ->
            x.name.contains(changedSearchText, true)
        }
        matchedProducts.value = productsFromSearch
    }

    fun onClearClick() {
        searchText.value = ""
        matchedProducts.value = arrayListOf()
    }
}
