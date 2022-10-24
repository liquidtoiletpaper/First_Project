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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.screens.*
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.MyPurchases
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
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "mainPage") {
                        composable("mainPage") { MainPage(navController) }
                        composable("homeScreen") { HomeScreen() }
                        composable("catalogScreen") { CatalogScreen(navController) }
                        composable("favoritesScreen") { FavoritesScreen() }
                        composable("cartScreen") { CartScreen(navController) }
                        composable("profileScreen") { ProfileScreen() }
                        composable("myPurchasesScreen") { MyPurchases(navController) }
                    }
                    MainPage(navController)
                }
                LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            }
        }
    }
}

@Composable
fun MainPage(navController : NavController) {
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
            content = {
                BottomNavGraph(navController = navController)
            },
            bottomBar = {
                BottomBar(navController = navController)
            },
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Catalog,
        BottomBarScreen.Favorites,
        BottomBarScreen.Cart,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    BottomNavigation(
        backgroundColor = DarkAppBarBackground,
        contentColor = PrimaryWhite
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
    ) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = ""
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route)
        }
    )
}