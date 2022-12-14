package ru.liquidtoiletpaper.myapplication.screens.catalogScreens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import ru.liquidtoiletpaper.myapplication.global.FavProductsList
import ru.liquidtoiletpaper.myapplication.global.ProductsCharacteristicsList
import ru.liquidtoiletpaper.myapplication.models.CharModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.productItem
import ru.liquidtoiletpaper.myapplication.requestProductCharacteristics
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.BorderLine
import ru.liquidtoiletpaper.myapplication.ui.theme.*

val productChar = Characteristics()
val charIsOpen = mutableStateOf(false)
val charRows = mutableStateListOf<Characteristics>()
val inFav = mutableStateOf(false)
@Composable
fun ItemProduct(navController: NavController){
    charRows.clear()
    charRows.add(productChar)
    val context = LocalContext.current
    requestProductCharacteristics(productItem.productId, context) { response ->
        val shell = Json.decodeFromString<ResponseShell>(response.toString())
        if (shell.status == "success") {
            val productCharModel = Json.decodeFromJsonElement<CharModel>(shell.content!!)
            productChar.product_id = productCharModel.product_id
            productChar.country = productCharModel.country
            if(productCharModel.warranty != null) {
                productChar.warranty = productCharModel.warranty
            }
            productChar.model = productCharModel.model
            productChar.brand = productCharModel.brand
            productChar.length = productCharModel.length
            productChar.width = productCharModel.width
            productChar.height = productCharModel.height
            productChar.weight = productCharModel.weight
            productChar.gpu_graphicsController = productCharModel.gpu_graphicsController
            productChar.gpu_memoryType = productCharModel.gpu_memoryType
            productChar.gpu_memorySize = productCharModel.gpu_memorySize
            productChar.cpu_socket = productCharModel.cpu_socket
            productChar.cpu_coreAmount = productCharModel.cpu_coreAmount
            productChar.cpu_frequency = productCharModel.cpu_frequency
            productChar.cpu_consumption = productCharModel.cpu_consumption
            productChar.ps_power = productCharModel.ps_power
        }
    }
    if(!charIsOpen.value){
        ItemProductScreen(navController = navController)
    } else {
        CharScreen(navController)
        BackHandler(true) {
            charIsOpen.value = false
        }
    }
}

@Composable
fun CharScreen(navController: NavController){
    val listState = rememberLazyListState()
    BackHandler(true) {
        charIsOpen.value = false
    }
    Scaffold(
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
                                    charIsOpen.value = false
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Close",
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
                                text = "????????????????????????????",
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ){

                        }
                    },
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            items(charRows) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp)
                    ){
                        Column(
                            modifier = Modifier
                                .padding(vertical = 15.dp)
                        ){
                            Text(
                                text = "?????????????????? ????????????",
                                textAlign = TextAlign.Start,
                                style = Typography.h1
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            ){
                                Text(
                                    text = "????????????????",
                                    textAlign = TextAlign.Start,
                                    style = HugeTypography.body2,
                                    modifier = Modifier
                                        .weight(1f)
                                )
                                Box(modifier = Modifier.weight(0.12f))
                                val warrantyText: String = if(productChar.warranty != 0){
                                    "${productChar.warranty} ??????????????"
                                } else {
                                    "??????"
                                }
                                Text(
                                    text = warrantyText,
                                    textAlign = TextAlign.Start,
                                    style = HugeTypography.body1,
                                    modifier = Modifier
                                        .weight(1f)
                                )
                            }
                            productChar.country?.let { it1 -> CharRowHuge(title = "????????????", char = it1) }
                        }
                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .padding(vertical = 15.dp)
                        ){
                            Text(
                                text = "?????????? ??????????????????",
                                textAlign = TextAlign.Start,
                                style = Typography.h1
                            )
                            productChar.model?.let { it1 -> CharRowHuge(title = "????????????", char = it1) }
                            productChar.brand?.let { it1 -> CharRowHuge(title = "??????????????????????????", char = it1) }
                        }
                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .padding(vertical = 15.dp)
                        ){
                            Text(
                                text = "???????????????? ??????????????????",
                                textAlign = TextAlign.Start,
                                style = Typography.h1
                            )
                            productChar.gpu_graphicsController?.let { it1 -> CharRowHuge(title = "?????????????????????? ??????????????????", char = it1) }
                            productChar.gpu_memoryType?.let { it1 -> CharRowHuge(title = "?????? ????????????", char = it1) }
                            productChar.gpu_memorySize?.let { it1 -> CharRowHuge(title = "?????????? ??????????????????????", char = it1.toString(), char2 = "????") }
                            productChar.cpu_socket?.let { it1 -> CharRowHuge(title = "??????????", char = it1) }
                            productChar.cpu_coreAmount?.let { it1 -> CharRowHuge(title = "?????????? ???????????????????? ????????", char = it1.toString(), char2 = "????.") }
                            productChar.cpu_frequency?.let { it1 -> CharRowHuge(title = "?????????????? ?????????????? ????????????????????", char = it1.toString(), char2 = "??????") }
                            productChar.cpu_consumption?.let { it1 -> CharRowHuge(title = "???????????????????????????? (TDP)", char = it1.toString(), char2 = "????") }
                            productChar.ps_power?.let { it1 -> CharRowHuge(title = "????????????????", char = it1.toString(), char2 = "????") }
                        }
                        if(productChar.length != null || productChar.width != null || productChar.height != null || productChar.weight != null){
                            Column(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .padding(vertical = 15.dp)
                            ){
                                Text(
                                    text = "???????????????????? ??????????????",
                                    textAlign = TextAlign.Start,
                                    style = Typography.h1
                                )
                                productChar.length?.let { it1 -> CharRowHuge(title = "??????????", char = it1.toString(), char2 = "????") }
                                productChar.width?.let { it1 -> CharRowHuge(title = "????????????", char = it1.toString(), char2 = "????") }
                                productChar.height?.let { it1 -> CharRowHuge(title = "????????????", char = it1.toString(), char2 = "????") }
                                productChar.weight?.let { it1 -> CharRowHuge(title = "??????", char = it1.toString(), char2 = "????") }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemProductScreen(navController: NavController){
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
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
                            val icon: ImageVector =
                                if (productItem in FavProductsList.products) {
                                    Icons.Filled.Favorite
                                } else {
                                    Icons.Filled.FavoriteBorder
                                }
                            val tint: Color = if (productItem in FavProductsList.products) {
                                FavColor
                            } else {
                                PrimaryWhite
                            }

                            IconButton(
                                onClick = {

                                },
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Fav",
                                    modifier = Modifier,
                                    tint = tint,
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
                            text = "${productItem.cost} ???",
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
                                Toast.makeText(context, "?????????? ????????????????", Toast.LENGTH_SHORT).show()
                                clicked.value = false
                            },
                        ) {
                            Text(
                                text = "?? ??????????????",
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
                    "1" -> { categoryTitle = "????????????????????" }
                    "2" -> { categoryTitle = "??????????????????" }
                    "3" -> { categoryTitle = "???????? ??????????????" }
                    "4" -> { categoryTitle = "?????????????????????? ??????????" }
                    "5" -> { categoryTitle = "?????????????? ????????" }
                    "6" -> { categoryTitle = "??????????" }
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
                        text = "${productItem.cost} ???",
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
                                charIsOpen.value = true
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
                                    text = "????????????????????????????",
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
                        Column(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                        ){
                            Text(
                                text = "?????????????????? ????????????",
                                textAlign = TextAlign.Start,
                                style = Typography.h5
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            ){
                                Text(
                                    text = "????????????????",
                                    textAlign = TextAlign.Start,
                                    style = Typography.body2,
                                    modifier = Modifier
                                        .weight(1f)
                                )
                                Box(modifier = Modifier.weight(0.2f))
                                val warrantyText: String = if(productChar.warranty != 0){
                                    "${productChar.warranty} ??????????????"
                                } else {
                                    "??????"
                                }
                                Text(
                                    text = warrantyText,
                                    textAlign = TextAlign.Start,
                                    style = Typography.body1,
                                    modifier = Modifier
                                        .weight(1f)
                                )
                            }
                            productChar.country?.let { it1 -> CharRow(title = "????????????", char = it1) }
                        }
                        Column(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                        ){
                            Text(
                                text = "?????????? ??????????????????",
                                textAlign = TextAlign.Start,
                                style = Typography.h5
                            )
                            productChar.model?.let { it1 -> CharRow(title = "????????????", char = it1) }
                            productChar.brand?.let { it1 -> CharRow(title = "??????????????????????????", char = it1) }
                        }
                        Column(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                        ){
                            Text(
                                text = "???????????????? ??????????????????",
                                textAlign = TextAlign.Start,
                                style = Typography.h5
                            )
                            productChar.gpu_graphicsController?.let { it1 -> CharRow(title = "?????????????????????? ??????????????????", char = it1) }
                            productChar.gpu_memorySize?.let { it1 -> CharRow(title = "?????????? ??????????????????????", char = it1.toString(), char2 = "????") }
                            productChar.cpu_socket?.let { it1 -> CharRow(title = "??????????", char = it1) }
                            productChar.cpu_coreAmount?.let { it1 -> CharRow(title = "?????????? ???????????????????? ????????", char = it1.toString(), char2 = "????.") }
                            productChar.cpu_frequency?.let { it1 -> CharRow(title = "?????????????? ?????????????? ????????????????????", char = it1.toString(), char2 = "??????") }
                            productChar.ps_power?.let { it1 -> CharRow(title = "????????????????", char = it1.toString(), char2 = "????") }
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
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(modifier = Modifier.weight(1f)){
            Text(
                text = title,
                textAlign = TextAlign.Start,
                style = Typography.body2,
                modifier = Modifier
            )
        }
        Box(modifier = Modifier.weight(0.2f))
        val text: String = if (char2 != null) {
            "$char $char2"
        } else {
            char
        }
        Box(modifier = Modifier.weight(1f)){
            Text(
                text = text,
                textAlign = TextAlign.Start,
                style = Typography.body1,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun CharRowHuge(title: String, char: String, char2: String? = null) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(modifier = Modifier.weight(1f)){
            Text(
                text = title,
                textAlign = TextAlign.Start,
                style = HugeTypography.body2,
                modifier = Modifier
            )
        }
        Box(modifier = Modifier.weight(0.12f))
        val text: String = if (char2 != null) {
            "$char $char2"
        } else {
            char
        }
        Box(modifier = Modifier.weight(1f)){
            Text(
                text = text,
                textAlign = TextAlign.Start,
                style = HugeTypography.body1,
                modifier = Modifier
            )
        }
    }
}