package ru.liquidtoiletpaper.myapplication.screens.catalogScreens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.liquidtoiletpaper.myapplication.ProductItem
import ru.liquidtoiletpaper.myapplication.global.FilteredProductsList
import ru.liquidtoiletpaper.myapplication.screens.rememberForeverLazyListState

@Composable
fun CategorySearch(navController: NavController){
    val context = LocalContext.current
    for(product in FilteredProductsList.products){
        ProductItem(product = product)
    }
}