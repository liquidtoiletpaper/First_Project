package ru.liquidtoiletpaper.myapplication.screens.catalogScreens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.liquidtoiletpaper.myapplication.ProductItem
import ru.liquidtoiletpaper.myapplication.global.FilteredProductsList

@Composable
fun CategorySearch(navController: NavController){
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
    ){
        for(product in FilteredProductsList.products){
            item {
                ProductItem(product = product, navController)
            }
        }
    }
}