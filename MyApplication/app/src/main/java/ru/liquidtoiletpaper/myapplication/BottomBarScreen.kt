package ru.liquidtoiletpaper.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Catalog: BottomBarScreen(
        route = "catalog",
        title = "Catalog",
        icon = Icons.Default.Search
    )
    object Favorites: BottomBarScreen(
        route = "favorites",
        title = "Favs",
        icon = Icons.Default.Favorite
    )
    object Cart: BottomBarScreen(
        route = "cart",
        title = "Cart",
        icon = Icons.Default.ShoppingCart
    )
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}