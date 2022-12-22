package ru.liquidtoiletpaper.myapplication.screens.catalogScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.characteristics.CharVideocard
import ru.liquidtoiletpaper.myapplication.characteristics.Characteristics
import ru.liquidtoiletpaper.myapplication.global.ProductsCharacteristicsList
import ru.liquidtoiletpaper.myapplication.models.CharModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.productItem
import ru.liquidtoiletpaper.myapplication.requestProductCharacteristics
import ru.liquidtoiletpaper.myapplication.ui.theme.*


val productChar = Characteristics()
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemProduct(navController: NavController){
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            Column(){
                TopAppBar(
                    backgroundColor = DarkAppBarBackground,
                    contentColor = Color.White,
                    title = {
                        Box(
                            Modifier
                                .weight(1f)
                                .padding(end = 20.dp)) {
                            IconButton(
                                onClick = {
                                    navController.popBackStack()
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
                        Row(
                            modifier = Modifier
                                .weight(6f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                modifier = Modifier,
                                text = productItem.name,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ){
                            IconButton(
                                onClick = {

                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.FavoriteBorder,
                                    contentDescription = "Search",
                                    modifier = Modifier,
                                    tint = PrimaryWhite,
                                )
                            }
                        }
                    },
                )
            }
        }
    ){ paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
        ){

            requestProductCharacteristics(productItem.productId, context) { response ->
                val shell = Json.decodeFromString<ResponseShell>(response.toString())
                if (shell.status == "success") {
                    val productCharModel = Json.decodeFromJsonElement<CharModel>(shell.content!!)
                    productChar.product_id = productCharModel.product_id
                    if(productCharModel.country != null){
                        productChar.country = productCharModel.country
                    }
                    if(productCharModel.warranty != null) {
                        productChar.warranty = productCharModel.warranty
                    }
                    if(productCharModel.model != null) {
                        productChar.model = productCharModel.model
                    }
                    if(productCharModel.brand != null) {
                        productChar.brand = productCharModel.brand
                    }
                    if(productCharModel.length != null) {
                        productChar.length = productCharModel.length
                    }
                    if(productCharModel.width != null) {
                        productChar.width = productCharModel.width
                    }
                    if(productCharModel.height != null) {
                        productChar.height = productCharModel.height
                    }
                    if(productCharModel.weight != null) {
                        productChar.weight = productCharModel.weight
                    }
                    if(productCharModel.gpu_graphicsController != null) {
                        productChar.gpu_graphicsController = productCharModel.gpu_graphicsController
                    }
                    if(productCharModel.gpu_memorySize != null) {
                        productChar.gpu_memorySize = productCharModel.gpu_memorySize
                    }
                    if(productCharModel.cpu_socket != null) {
                        productChar.cpu_socket = productCharModel.cpu_socket
                    }
                    if(productCharModel.cpu_coreAmount != null) {
                        productChar.cpu_coreAmount = productCharModel.cpu_coreAmount
                    }
                    if(productCharModel.cpu_frequency != null) {
                        productChar.cpu_frequency = productCharModel.cpu_frequency
                    }
                    if(productCharModel.cpu_consumption != null) {
                        productChar.cpu_consumption = productCharModel.cpu_consumption
                    }
                    if(productCharModel.ps_power != null) {
                        productChar.ps_power = productCharModel.ps_power
                    }
                }
            }
            item{
                Column(Modifier.fillMaxWidth()){
                    GlideImage(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(horizontal = 60.dp)
                            .padding(top = 20.dp)
                            .heightIn(max = 200.dp)
                            .wrapContentSize(),
                        model = "https://tautaste.ru/images?image=${productItem.image}",
                        contentDescription = productItem.image,
                        contentScale = ContentScale.Fit,
                    )
                }
                var categoryTitle = ""
                when (productItem.category) {
                    "1" -> { categoryTitle = "Видеокарта" }
                    "2" -> { categoryTitle = "Процессор" }
                    "3" -> { categoryTitle = "Блок питания" }
                    "4" -> { categoryTitle = "Материнская плата" }
                    "5" -> { categoryTitle = "Жёсткий диск" }
                    "6" -> { categoryTitle = "Кулер" }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ){
                    Text(
                        text = "$categoryTitle ${productItem.name}",
                        textAlign = TextAlign.Start,
                        style = HugeTypography.body1,
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 5.dp),
                        text = "${productItem.cost} ₽",
                        textAlign = TextAlign.Start,
                        style = HugeTypography.h1,
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .clickable {

                            }
                            .padding(horizontal = 20.dp)
                            .padding(vertical = 10.dp)
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column(
                                modifier = Modifier
                                    .weight(10f)
                            ){
                                Text(
                                    text = "Характеристики",
                                    textAlign = TextAlign.Start,
                                    style = Typography.h1
                                )
                            }
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowRight,
                                contentDescription = "Open",
                                modifier = Modifier
                                    .weight(1f),
                                tint = SecondaryText,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp)
                    ){
                        when(productItem.category) {
                            "1" -> {
                                Column(
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                ){
                                    Text(
                                        text = "Заводские данные",
                                        textAlign = TextAlign.Start,
                                        style = Typography.h5
                                    )
                                    Row(){
                                        Text(
                                            text = "Гарантия",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body2,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Text(
                                            text = "${productChar.warranty}",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body1,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                    }
                                    Row(){
                                        Text(
                                            text = "Страна",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body2,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Text(
                                            text = productChar.country,
                                            textAlign = TextAlign.Start,
                                            style = Typography.body1,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                ){
                                    Text(
                                        text = "Общие параметры",
                                        textAlign = TextAlign.Start,
                                        style = Typography.h5
                                    )
                                    Row(){
                                        Text(
                                            text = "Модель",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body2,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Text(
                                            text = productChar.model,
                                            textAlign = TextAlign.Start,
                                            style = Typography.body1,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                    }
                                    Row(){
                                        Text(
                                            text = "Производитель",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body2,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Text(
                                            text = productChar.brand,
                                            textAlign = TextAlign.Start,
                                            style = Typography.body1,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                ){
                                    Text(
                                        text = "Основные параметры",
                                        textAlign = TextAlign.Start,
                                        style = Typography.h5
                                    )
                                    Row(){
                                        Text(
                                            text = "Графический процессор",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body2,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Text(
                                            text = productChar.gpu_graphicsController,
                                            textAlign = TextAlign.Start,
                                            style = Typography.body1,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                    }
                                    Row(){
                                        Text(
                                            text = "Объем видеопамяти",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body2,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Text(
                                            text = "${productChar.gpu_memorySize} GB",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body1,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}