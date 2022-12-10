package ru.liquidtoiletpaper.myapplication

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.global.ProductsList
import ru.liquidtoiletpaper.myapplication.global.User
import ru.liquidtoiletpaper.myapplication.models.ProductModel
import ru.liquidtoiletpaper.myapplication.models.ProductsModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.screens.*
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.*
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
        composable("helpScreen") { Help(navController) }
        composable("reviewsScreen") { Reviews(navController) }
    }
}

fun makeRequest(context: Context, url: String, parameters: Map<String, String>?, callback: (response: String?) -> Unit) {
    val request = object : StringRequest(
        Method.POST, url,
        Response.Listener { response ->
            callback.invoke(response)
        },
        Response.ErrorListener { error ->
            println(error)
        }
    ) {
        override fun getParams(): Map<String, String>? {
            return parameters
        }
    }
    VolleySingleton.getInstance(context).addToRequestQueue(request)
}

fun requestProduct(id: Int, context: Context, callback: (response: String?) -> Unit) {
    val url = "http://tautaste.ru/getProduct?product_id=$id"
    val queue = Volley.newRequestQueue(context)
    val request = object : StringRequest(
        Method.GET, url,
        Response.Listener { response ->
            callback.invoke(response)
        },
        Response.ErrorListener { error ->
            println(error)
        }
    ){

    }
    VolleySingleton.getInstance(context).addToRequestQueue(request)
}
/*
* */
fun updateRequest(context: Context, url: String, parameters: Map<String, String>?, callback: (response: String?) -> Unit) {
    val request = object : StringRequest(
        Method.POST, url,
        Response.Listener { response ->
            callback.invoke(response)
        },
        Response.ErrorListener { error ->
            println(error)
        }) {
        override fun getParams(): Map<String, String>? {
            return parameters
        }
    }
    VolleySingleton.getInstance(context).addToRequestQueue(request)
}

fun requestProducts(context: Context, callback: (response: String?) -> Unit) {
    val url = "http://tautaste.ru/getProducts"
    val queue = Volley.newRequestQueue(context)
    val request = StringRequest(
        Request.Method.GET, url,
        { //result ->
            //println(result)
                result -> Log.d("MyLog", "Result: $result")
        },
        { //error ->
                error -> Log.d("MyLog", "Error: $error")
            //println(error)
        }
    )
    //queue.add(request)
    VolleySingleton.getInstance(context).addToRequestQueue(request)
}

fun updateProducts(context: Context){
    ProductsList.clearProducts()
    for(i in 1..5){
        requestProduct(i, context) { response ->
            val shell = Json.decodeFromString<ResponseShell>(response.toString())
            if (shell.status == "success") {
                val product = Product()
                val productModel = Json.decodeFromJsonElement<ProductModel>(shell.content!!)
                product.productId = productModel.product_id
                product.image = productModel.image
                product.name = productModel.name
                product.description = productModel.description
                product.category = productModel.category
                product.cost = productModel.cost
                ProductsList.addProducts(product)
                Log.d("MyLog", product.productId.toString())
            }
        }
    }
}


var productsSize = 0


@Composable
fun MainPage() {
    val context = LocalContext.current
    updateProducts(context)
    fun requestUserData(id: Int) {
        val url = "http://tautaste.ru/getData?id=$id"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET, url,
            { //result ->
                //println(result)
                result -> Log.d("MyLog", "Result: $result")
            },
            { //error ->
                error -> Log.d("MyLog", "Error: $error")
                //println(error)
            }
        )
        //queue.add(request)
        VolleySingleton.getInstance(context).addToRequestQueue(request)
    }

    requestProducts(context) { response ->
        val shell = Json.decodeFromString<ResponseShell>(response.toString())
        if(shell.status == "success") {
            val productsModel = Json.decodeFromJsonElement<ProductsModel>(shell.content!!)
            productsSize = productsModel.product.size
        }
    }

    val navController = rememberNavController()
    var key = false
    fun validate(length: Int, minLength: Int, maxLength: Int) {
        if (length < minLength || length > maxLength) {
            key = true
        }
    }
    //requestUserData(User.id)
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
                            icon = Icons.Default.Search
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
        ) { padding ->
            Column(Modifier.padding(padding)){
                Navigation(navController = navController)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product){
    Column(modifier = Modifier.fillMaxWidth()){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .clickable { }
        ) {
            Column(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    // product name
                    text = product.name,
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.h1,
                    fontSize = 16.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Start,
                    letterSpacing = 0.5.sp,
                    maxLines = 2
                )
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    // product description
                    text = product.description,
                    color = SecondaryText,
                    style = MaterialTheme.typography.body1,
                    fontSize = 11.sp,
                    fontFamily = NormalFont,
                    textAlign = TextAlign.Justify,
                    letterSpacing = 0.5.sp,
                    maxLines = 3
                )
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    // product cost
                    text = "${product.cost} ₽",
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.h1,
                    fontSize = 14.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.5.sp
                )
            }
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

@Composable
fun DropdownMenu(){
    var mExpanded by remember { mutableStateOf(false) }
    val mGender = listOf("Мужской", "Женский")
    var mSelectedText by remember { mutableStateOf(mGender[0]) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    User.gender = mSelectedText
    Column(
        Modifier
            .padding(top = 10.dp)) {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = TextFieldBackground,
                cursorColor = Color.Black,
                disabledLabelColor = DisabledText,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = DisabledText
            ),
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            enabled = false,
            modifier = Modifier
                .clickable { mExpanded = !mExpanded }
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {
                Text(
                    text = "Пол",
                    fontFamily = NormalFont,
                    color = PrimaryTextField
                )
            },
            trailingIcon = {
                Icon(icon,"contentDescription")
            }
        )
        MaterialTheme(
            colors = MaterialTheme.colors.copy(surface = TextFieldBackground),
            shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(5))
        ){
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                mGender.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            mSelectedText = label
                            mExpanded = false
                        },
                    ) {
                        Text(
                            text = label,
                        )
                    }
                }
            }
        }
    }
}