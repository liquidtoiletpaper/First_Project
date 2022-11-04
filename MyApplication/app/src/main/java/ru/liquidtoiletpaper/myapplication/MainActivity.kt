package ru.liquidtoiletpaper.myapplication

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.screens.*
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.City
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.MyPurchases
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.MyPurchasesOrder
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.ProfileInfo
import ru.liquidtoiletpaper.myapplication.ui.theme.*

class MainActivity : ComponentActivity() {

    /// Заблокировать поворот экрана на устройстве
    @Composable
    fun LockScreenOrientation(orientation: Int) {
        val context = LocalContext.current
        DisposableEffect(Unit) {
            val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
            val originalOrientation = requestedOrientation
            requestedOrientation = orientation
            onDispose {
                requestedOrientation = originalOrientation
            }
        }
    }
    fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainPage()
                }
                LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("mainScreen") { MainPage() }
        composable("homeScreen") { HomeScreen() }
        composable("catalogScreen") { CatalogScreen() }
        composable("favoritesScreen") { FavoritesScreen() }
        composable("cartScreen") { CartScreen(navController) }
        composable("profileScreen") { ProfileScreen(navController) }
        composable("myPurchasesScreen") { MyPurchases(navController) }
        composable("myPurchasesOrderScreen") { MyPurchasesOrder(navController) }
        composable("cityScreen") { City(navController) }
        composable("profileInfoScreen") { ProfileInfo(navController) }
    }
}

@Composable
fun MainPage() {
    val context = LocalContext.current
    val navController = rememberNavController()
    var key = false
    fun validate(length: Int, minLength: Int, maxLength: Int) {
        if (length < minLength || length > maxLength) {
            key = true
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(PrimaryPageBackground),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

        }
        Scaffold(
            topBar = {
                /*
                TopAppBar(
                    backgroundColor = DarkAppBarBackground,
                    contentColor = Color.White,
                    contentPadding = PaddingValues(vertical = 5.dp)
                ) {
                    var isErrorSearch by rememberSaveable { mutableStateOf(false) }
                    var searchText by remember { mutableStateOf("") }
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = TextFieldBackground,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 5.dp)
                            .border(width = 1.dp, color = SecondaryButton, shape = Shapes.small),
                        singleLine = true,

                        value = searchText,
                        onValueChange = { searchText = it.take(128) },
                        placeholder = {
                            Image(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(PrimaryTextField)
                            )
                            Text(
                                "Search",
                                fontFamily = NormalFont,
                                modifier = Modifier
                                    .padding(start = 30.dp)
                            )
                        },
                        shape = RoundedCornerShape(5.dp),
                        isError = isErrorSearch,
                        keyboardActions = KeyboardActions {
                            validate(
                                searchText.length,
                                6,
                                72
                            ); isErrorSearch = key
                        }
                    )
                }
                 */
            },
            bottomBar = {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavItem(
                            title = "Home",
                            route = "homeScreen",
                            icon = Icons.Default.Home
                        ),
                        BottomNavItem(
                            title = "Catalog",
                            route = "catalogScreen",
                            icon = Icons.Default.List
                        ),
                        BottomNavItem(
                            title = "Favs",
                            route = "favoritesScreen",
                            icon = Icons.Default.Favorite
                        ),
                        BottomNavItem(
                            title = "Cart",
                            route = "cartScreen",
                            icon = Icons.Default.ShoppingCart
                        ),
                        BottomNavItem(
                            title = "Profile",
                            route = "profileScreen",
                            icon = Icons.Default.Person
                        )
                    ),
                    navController = navController,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            },
        ) {
            Navigation(navController = navController)
        }
    }
}



@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = DarkAppBarBackground,
        contentColor = PrimaryWhite,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = item.route == navController.currentDestination?.route,
                onClick = { onItemClick(item) },
                selectedContentColor = PrimaryWhite,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            fontFamily = NormalFont,
                            style = MaterialTheme.typography.body1,
                            fontSize = 11.sp
                        )
                    }
                }
            )
        }
    }
}