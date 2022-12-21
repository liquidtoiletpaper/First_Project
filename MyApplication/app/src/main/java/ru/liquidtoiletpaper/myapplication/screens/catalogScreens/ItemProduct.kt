package ru.liquidtoiletpaper.myapplication.screens.catalogScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.liquidtoiletpaper.myapplication.productItem
import ru.liquidtoiletpaper.myapplication.screens.Product
import ru.liquidtoiletpaper.myapplication.screens.catalogItem1IsOpen
import ru.liquidtoiletpaper.myapplication.screens.searchIsOpen
import ru.liquidtoiletpaper.myapplication.ui.theme.*

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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight()
        ){
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
                when(productItem.category) {
                    "1" -> {
                        Column(){
                            Text(
                                text = ""
                            )
                            Row(){

                            }
                            Row(){

                            }
                            Row(){

                            }
                            Row(){

                            }

                        }
                    }
                }
            }
        }
    }
}