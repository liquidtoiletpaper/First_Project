package ru.liquidtoiletpaper.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.ProductItem
import ru.liquidtoiletpaper.myapplication.ui.theme.PrimaryPageBackground

@Composable
fun CatalogScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .background(PrimaryPageBackground),
        bottomBar = {

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}