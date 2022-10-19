package ru.liquidtoiletpaper.myapplication

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(GreetingPageBackground),
    ) {
        var key = false
        fun validate(length: Int, minLength: Int, maxLength: Int) {
            if (length < minLength || length > maxLength) {
                key = true
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            var isErrorSearch by rememberSaveable { mutableStateOf(false) }
            var searchText by remember { mutableStateOf("") }
            OutlinedTextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = TextFieldBackground,
                    cursorColor = androidx.compose.ui.graphics.Color.Black,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 5.dp),
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
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
        ){
            /*
            BottomAppBar(
                modifier: Modifier = Modifier,
                backgroundColor: Color = MaterialTheme.colors.primarySurface,
                contentColor: Color = contentColorFor(backgroundColor),
                cutoutShape: Shape? = null,
                elevation: Dp = AppBarDefaults.BottomAppBarElevation,
                contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
                content: RowScope.() -> Unit
            )
             */
            BottomAppBar(
                modifier = Modifier
                //.background(DarkAppBarBackground),
                ,
                backgroundColor = DarkAppBarBackground,
                contentColor = Color.White,
                contentPadding = PaddingValues(horizontal = 30.dp)

            ) {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Меню")
                }

                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Info, contentDescription = "Информация о приложении")
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Search, contentDescription = "Избранное")
                }
            }
        }
    }
}