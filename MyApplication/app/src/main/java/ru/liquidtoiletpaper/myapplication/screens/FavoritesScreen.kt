package ru.liquidtoiletpaper.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun FavoritesScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {

        }
    }
}