package ru.liquidtoiletpaper.myapplication.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.*
import ru.liquidtoiletpaper.myapplication.R
import ru.liquidtoiletpaper.myapplication.global.ProductsList
import ru.liquidtoiletpaper.myapplication.models.ProductModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun CatalogScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .background(PrimaryPageBackground),
        bottomBar = {

        },
        topBar = {
            TopAppBar(
                backgroundColor = DarkAppBarBackground,
                contentColor = Color.White,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Каталог",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                    )
                }

            }
        }
    ) { padding ->
        LazyColumn(
            state = rememberForeverLazyListState(key = "Catalog"),
            modifier = Modifier
                .padding(padding)
        ) {
            item {
                CatalogTitle(title = "Компьютерные комплектующие")
                if(catalogTitleIsOpen.value){
                    VExpandAnimation(time = 200, check = catalogTitleIsOpen){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp)
                        ) {
                            CatalogItem(R.drawable.ic_video_card, "Видеокарты", "1", navController)
                            CatalogItem(R.drawable.ic_cpu, "Процессоры", "2", navController)
                            CatalogItem(
                                R.drawable.ic_power_supply,
                                "Блоки питания",
                                "3",
                                navController
                            )
                            CatalogItem(
                                R.drawable.ic_motherboard,
                                "Материнские платы",
                                "1",
                                navController
                            )
                            CatalogItem(
                                R.drawable.ic_ssd,
                                "Жёсткие диски (SSD)",
                                "1",
                                navController
                            )
                            CatalogItem(
                                R.drawable.ic_cooler,
                                "Кулеры для процессоров",
                                "1",
                                navController
                            )
                        }
                    }
                }
            }
        }
    }
}
private val catalogTitleIsOpen = mutableStateOf(false)

@Composable
fun CatalogTitle(title: String){
    var mExpanded by remember { mutableStateOf(false) }
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .clickable {
                mExpanded = !mExpanded
                catalogTitleIsOpen.value = !catalogTitleIsOpen.value
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkAppBarBackground)
                .padding(start = 20.dp, end = 10.dp)
                .padding(vertical = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = title,
                    maxLines = 2,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        tint = SecondaryText
                    )
                }
            }
        }
    }
}


@Composable
fun CatalogItem(image: Int, title: String, category: String, navController: NavController){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .clickable {
                ProductsList.clearProducts()
                for (i in 1..5) {
                    requestProduct(i, context) { response ->
                        val shell = Json.decodeFromString<ResponseShell>(response.toString())
                        if (shell.status == "success") {
                            val product = Product()
                            val productModel =
                                Json.decodeFromJsonElement<ProductModel>(shell.content!!)
                            product.productId = productModel.product_id
                            product.image = productModel.image
                            product.name = productModel.name
                            product.description = productModel.description
                            product.category = productModel.category
                            product.cost = productModel.cost
                            if (product.category == category) {
                                ProductsList.addProducts(product)
                            }
                        }
                    }
                }
                navController.navigate("homeScreen")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkAppBarBackground)
                .padding(start = 20.dp, end = 10.dp)
                .padding(vertical = 5.dp)
                .height(55.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = "",
                    modifier = Modifier
                        .width(40.dp),
                )
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Image(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(SecondaryText),
                    )
                }
            }
        }
    }
}


@Composable
fun CategoryItem(){
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .clickable {

            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkAppBarBackground)
                .padding(start = 20.dp, end = 10.dp)
                .padding(vertical = 12.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(PrimaryWhite)
                )
                Text(
                    text = "Reviews",
                    maxLines = 1,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Image(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(SecondaryText),
                    )
                }
            }
        }
    }
}


private val SaveMap = mutableMapOf<String, KeyParams>()

private data class KeyParams(
    val params: String = "",
    val index: Int,
    val scrollOffset: Int
)

/**
 * Save scroll state on all time.
 * @param key value for comparing screen
 * @param params arguments for find different between equals screen
 * @param initialFirstVisibleItemIndex see [LazyListState.firstVisibleItemIndex]
 * @param initialFirstVisibleItemScrollOffset see [LazyListState.firstVisibleItemScrollOffset]
 */
@Composable
fun rememberForeverLazyListState(
    key: String,
    params: String = "",
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0
): LazyListState {
    val scrollState = rememberSaveable(saver = LazyListState.Saver) {
        var savedValue = SaveMap[key]
        if (savedValue?.params != params) savedValue = null
        val savedIndex = savedValue?.index ?: initialFirstVisibleItemIndex
        val savedOffset = savedValue?.scrollOffset ?: initialFirstVisibleItemScrollOffset
        LazyListState(
            savedIndex,
            savedOffset
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            val lastIndex = scrollState.firstVisibleItemIndex
            val lastOffset = scrollState.firstVisibleItemScrollOffset
            SaveMap[key] = KeyParams(params, lastIndex, lastOffset)
        }
    }
    return scrollState
}