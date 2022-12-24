package ru.liquidtoiletpaper.myapplication.screens.catalogScreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.BorderLine
import ru.liquidtoiletpaper.myapplication.ui.theme.*


val productChar = Characteristics()
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemProduct(navController: NavController){
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
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
    val charRows = remember { mutableStateListOf<Characteristics>() }
    charRows.clear()
    charRows.add(productChar)
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
        },
        bottomBar = {
            Column(Modifier.fillMaxWidth()){
                Divider()
                Row(
                    modifier = Modifier
                        .background(DarkAppBarBackground)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .padding(start = 20.dp),
                            text = "${productItem.cost} ₽",
                            textAlign = TextAlign.Start,
                            style = HugeTypography.h3,
                        )
                    }
                    Column() {
                        val clicked = remember { mutableStateOf(true) }
                        Button(
                            enabled = clicked.value,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = PrimaryButton,
                                contentColor = PrimaryWhite,
                                disabledBackgroundColor = SecondaryButton,
                                disabledContentColor = PrimaryWhite
                            ),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .padding(end = 20.dp),
                            shape = RoundedCornerShape(5.dp),
                            onClick = {
                                Toast.makeText(context, "Товар добавлен", Toast.LENGTH_SHORT).show()
                                clicked.value = false
                            },
                        ) {
                            Text(
                                text = "В корзину",
                                color = PrimaryWhite,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
                Divider()
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
            items(charRows) {
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
                                    Row(
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                    ){
                                        Text(
                                            text = "Гарантия",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body2,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Box(modifier = Modifier.weight(0.2f))
                                        if(productChar.warranty != 0){
                                            Text(
                                                text = "${productChar.warranty} месяцев",
                                                textAlign = TextAlign.Start,
                                                style = Typography.body1,
                                                modifier = Modifier
                                                    .weight(1f)
                                            )
                                        } else {
                                            Text(
                                                text = "нет",
                                                textAlign = TextAlign.Start,
                                                style = Typography.body1,
                                                modifier = Modifier
                                                    .weight(1f)
                                            )
                                        }
                                    }
                                    CharRow(title = "Страна", char = productChar.country)
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
                                    CharRow(title = "Модель", char = productChar.model)
                                    CharRow(title = "Производитель", char = productChar.brand)
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
                                    CharRow(title = "Графический процессор", char = productChar.gpu_graphicsController)
                                    CharRow(title = "Объем видеопамяти", char = productChar.gpu_memorySize.toString(), char2 = "GB")
                                }
                            }
                            "2" -> {
                                Column(
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                ){
                                    Text(
                                        text = "Заводские данные",
                                        textAlign = TextAlign.Start,
                                        style = Typography.h5
                                    )
                                    Row(
                                        modifier = Modifier
                                            .padding(top = 10.dp)
                                    ){
                                        Text(
                                            text = "Гарантия",
                                            textAlign = TextAlign.Start,
                                            style = Typography.body2,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        Box(modifier = Modifier.weight(0.2f))
                                        if(productChar.warranty != 0){
                                            Text(
                                                text = "${productChar.warranty} месяцев",
                                                textAlign = TextAlign.Start,
                                                style = Typography.body1,
                                                modifier = Modifier
                                                    .weight(1f)
                                            )
                                        } else {
                                            Text(
                                                text = "нет",
                                                textAlign = TextAlign.Start,
                                                style = Typography.body1,
                                                modifier = Modifier
                                                    .weight(1f)
                                            )
                                        }
                                    }
                                    CharRow(title = "Страна", char = productChar.country)
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
                                    CharRow(title = "Модель", char = productChar.model)
                                    CharRow(title = "Производитель", char = productChar.brand)
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
                                    CharRow(title = "Сокет", char = productChar.cpu_socket)
                                    CharRow(title = "Общее количество ядер", char = productChar.cpu_coreAmount.toString(), char2 = "шт.")
                                    CharRow(title = "Базовая частота процессора", char = productChar.cpu_frequency.toString(), char2 = "ГГц")
                                    CharRow(title = "Тепловыделение (TDP)", char = productChar.cpu_consumption.toString(), char2 = "Вт")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharRow(title: String, char: String, char2: String? = null) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ){
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.weight(1f)){
            Text(
                text = title,
                textAlign = TextAlign.Start,
                style = Typography.body2,
                modifier = Modifier
            )
        }
        Box(modifier = Modifier.weight(0.2f))
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.weight(1f)){
            if (char2 != null) {
                Text(
                    text = "$char $char2",
                    textAlign = TextAlign.Start,
                    style = Typography.body1,
                    modifier = Modifier
                )
            } else {
                Text(
                    text = char,
                    textAlign = TextAlign.Start,
                    style = Typography.body1,
                    modifier = Modifier
                )
            }
        }
    }
}