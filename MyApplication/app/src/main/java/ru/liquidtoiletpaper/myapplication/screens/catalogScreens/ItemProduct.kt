package ru.liquidtoiletpaper.myapplication.screens.catalogScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.liquidtoiletpaper.myapplication.screens.catalogItem1IsOpen
import ru.liquidtoiletpaper.myapplication.screens.searchIsOpen
import ru.liquidtoiletpaper.myapplication.ui.theme.DarkAppBarBackground
import ru.liquidtoiletpaper.myapplication.ui.theme.PrimaryWhite

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
                            if (catalogItem1IsOpen.value) {
                                IconButton(
                                    onClick = {

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
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ){

        }
    }
}