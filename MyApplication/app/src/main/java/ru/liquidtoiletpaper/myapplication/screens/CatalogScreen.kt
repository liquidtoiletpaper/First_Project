package ru.liquidtoiletpaper.myapplication.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import flow.rememberFlowWithLifecycle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.*
import ru.liquidtoiletpaper.myapplication.R
import ru.liquidtoiletpaper.myapplication.global.FilteredProductsList
import ru.liquidtoiletpaper.myapplication.global.ProductsList
import ru.liquidtoiletpaper.myapplication.models.ProductSearchModelState
import ru.liquidtoiletpaper.myapplication.models.ProductSearchViewModel
import ru.liquidtoiletpaper.myapplication.models.ProductsModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.screens.catalogScreens.CategorySearch
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.BorderLine
import ru.liquidtoiletpaper.myapplication.ui.theme.*


private val searchIsOpen = mutableStateOf(false)
private val catalogTitle1IsOpen = mutableStateOf(false)
private val catalogTitle2IsOpen = mutableStateOf(false)
private val catalogItem1IsOpen = mutableStateOf(false)


@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun CatalogScreen(navController: NavController) {
    val context = LocalContext.current

    var key = false
    fun validate(length: Int, minLength: Int, maxLength: Int) {
        if (length < minLength || length > maxLength) {
            key = true
        }
    }

    Column(
        modifier = Modifier
    ) {
        if(!searchIsOpen.value){
            TopAppBar(
                backgroundColor = DarkAppBarBackground,
                contentColor = Color.White,
                title = {
                    Box(Modifier.weight(1f)) {
                        if (catalogItem1IsOpen.value) {
                            IconButton(
                                onClick = {
                                    catalogItem1IsOpen.value = !catalogItem1IsOpen.value
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    modifier = Modifier,
                                    tint = PrimaryWhite,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(6f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            modifier = Modifier,
                            text = "Каталог",
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ){
                        IconButton(
                            onClick = {
                                searchIsOpen.value = true
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search",
                                modifier = Modifier,
                                tint = PrimaryWhite,
                            )
                        }
                    }
                },
            )
        }
        if(searchIsOpen.value){
            Column(){
                ProductSearchUI(navController = navController, productSearchViewModel = ProductSearchViewModel())
                BackHandler(true) {
                    searchIsOpen.value = false
                }
            }
        }

        if(!catalogItem1IsOpen.value){
            LazyColumn(){
                item(key = "searchString") {

                }
                item(key = "pc parts") {
                    CatalogTitle(title = "Компьютерные комплектующие", catalogTitle1IsOpen)
                    if (catalogTitle1IsOpen.value) {
                        VExpandAnimation(time = 50, check = catalogTitle1IsOpen) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 40.dp)
                            ) {
                                CatalogItem(
                                    R.drawable.ic_video_card,
                                    "Видеокарты",
                                    "1",
                                    navController
                                )
                                CatalogItem(
                                    R.drawable.ic_cpu,
                                    "Процессоры",
                                    "2",
                                    navController
                                )
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
                item(key = "other") {
                    CatalogTitle(title = "Разное", catalogTitle2IsOpen)
                    if(catalogTitle2IsOpen.value){
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp)
                        ) {
                            CatalogItem(
                                image = R.drawable.icon_papich,
                                title = "Ручной аскет",
                                category = "4",
                                navController = navController
                            )
                            CatalogItem(
                                image = R.drawable.ic_moon,
                                title = "Луна",
                                category = "4",
                                navController = navController
                            )
                            CatalogItem(
                                image = R.drawable.ic_launcher_background,
                                title = "Кусочек",
                                category = "4",
                                navController = navController
                            )
                            CatalogItem(
                                image = R.drawable.ic_launcher_foreground,
                                title = "Это че",
                                category = "4",
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
        if(catalogItem1IsOpen.value){
            CategorySearch(navController = navController)
            BackHandler(true) {
                catalogItem1IsOpen.value = false
            }
        }
    }

}



@Composable
fun CatalogTitle(title: String, openCheck: MutableState<Boolean>){
    val icon = if (openCheck.value)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        modifier = Modifier
            .clickable {
                openCheck.value = !openCheck.value
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
        BorderLine()
    }
}


@Composable
fun CatalogItem(image: Int, title: String, category: String, navController: NavController){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clickable {
                FilteredProductsList.clearProducts()
                for (i in ProductsList.products) {
                    val product = Product()
                    product.productId = i.productId
                    product.image = i.image
                    product.name = i.name
                    product.description = i.description
                    product.category = i.category
                    product.cost = i.cost
                    if (product.category == category) {
                        FilteredProductsList.addProducts(product)
                    }
                }
                catalogItem1IsOpen.value = true
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkAppBarBackground)
                .padding(start = 20.dp, end = 10.dp)
                .padding(vertical = 5.dp)
                .height(55.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
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
        BorderLine()
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

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    var showClearButton by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }



    TopAppBar(
        backgroundColor = DarkAppBarBackground,
        contentColor = PrimaryWhite,
        title = { Text("") },
        navigationIcon = {
            IconButton(onClick = {
                onNavigateBack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier,
                    contentDescription = ""
                )
            }
        },
        actions = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .onFocusChanged { focusState ->
                        showClearButton = (focusState.isFocused)
                    }
                    .focusRequester(focusRequester),
                value = searchText,
                onValueChange = onSearchTextChanged,
                placeholder = {
                    Text(text = placeholderText, style = Typography.body2)
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                ),
                trailingIcon = {
                    AnimatedVisibility(
                        visible = showClearButton,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(onClick = { onClearClick() }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                tint = PrimaryWhite,
                                contentDescription = ""
                            )
                        }
                    }
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
            )
        }
    )


    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SearchBarUI(
    searchText: String,
    placeholderText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    matchesFound: Boolean,
    results: @Composable () -> Unit = {}
) {

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchBar(
                searchText,
                placeholderText,
                onSearchTextChanged,
                onClearClick,
                onNavigateBack
            )

            if (matchesFound) {
                Text("Results", modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(vertical = 5.dp)
                    .padding(horizontal = 20.dp), style = Typography.h5)
                results()
            } else {
                if (searchText.isNotEmpty()) {
                    NoSearchResults()
                }

            }
        }
    }
}

@Composable
fun Products(products: List<Product>?, onClick: (Product) -> Unit) {
    products?.forEach { product ->
        ProductRow(product = product) {
            onClick(product)
        }
        Divider()
    }
}


@Composable
fun ProductRow(product: Product, onClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        .padding(horizontal = 20.dp)
        .clickable { onClick() }) {
        Text(product.name, style = Typography.body1)
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun ProductSearchUI(navController: NavController, productSearchViewModel: ProductSearchViewModel) {
    val productSearchModelState by rememberFlowWithLifecycle(productSearchViewModel.productSearchModelState)
        .collectAsState(initial = ProductSearchModelState.Empty)
    SearchBarUI(
        searchText = productSearchModelState.searchText,
        placeholderText = "Поиск продуктов",
        onSearchTextChanged = { productSearchViewModel.onSearchTextChanged(it) },
        onClearClick = { productSearchViewModel.onClearClick() },
        onNavigateBack = {
            navController.popBackStack()
        },
        matchesFound = productSearchModelState.products.isNotEmpty()
    ) {

        Products(products = productSearchModelState.products) {
            navController.navigate("homeScreen")
        }
    }
}

@Composable
fun NoSearchResults() {

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text("No matches found")
    }
}