package ru.liquidtoiletpaper.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.liquidtoiletpaper.myapplication.screens.*

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Catalog.route){
            CatalogScreen(navController)
        }
        composable(route = BottomBarScreen.Favorites.route){
            FavoritesScreen()
        }
        composable(route = BottomBarScreen.Cart.route){
            CartScreen(navController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}