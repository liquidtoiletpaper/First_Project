package ru.liquidtoiletpaper.myapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.liquidtoiletpaper.myapplication.ui.theme.ErrorColor
import ru.liquidtoiletpaper.myapplication.ui.theme.PrimaryGreen
import ru.liquidtoiletpaper.myapplication.ui.theme.SemiBoldFont
import ru.liquidtoiletpaper.myapplication.ui.theme.Typography

@Composable
fun ItemOrder(navController: NavController){
    val context = LocalContext.current
    ItemOrderScreen(navController = navController)
}

@Composable
fun ItemOrderScreen(navController: NavController){
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        item {
            Column(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            color = Color.Black
                        ),
                        onClick = {
                            navController.navigate("MyPurchasesOrderScreen")
                            //return@clickable purchaseInfo
                        },
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 10.dp, top = 5.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (true) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Canceled",
                                tint = ErrorColor,
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Accepted",
                                tint = PrimaryGreen,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "№ 1111",
                                textAlign = TextAlign.Start,
                                style = Typography.h1,
                                modifier = Modifier
                                    .weight(3f)
                            )
                            Text(
                                text = "10.01.22",
                                style = Typography.body1,
                            )
                            Text(
                                text = "999 $",
                                fontFamily = SemiBoldFont,
                                style = Typography.h5,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(end = 20.dp),
                                maxLines = 1
                            )
                        }
                        if (true) {
                            Text(
                                text = "Canceled",
                                textAlign = TextAlign.Start,
                                style = Typography.body2,
                            )
                        } else {
                            Text(
                                text = "Accepted",
                                textAlign = TextAlign.Start,
                                style = Typography.body2,
                            )
                        }
                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            color = Color.Black
                        ),
                        onClick = {
                            navController.navigate("MyPurchasesOrderScreen")
                            //return@clickable purchaseInfo
                        },
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 10.dp, top = 5.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (false) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Canceled",
                                tint = ErrorColor,
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Accepted",
                                tint = PrimaryGreen,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "№ 1111",
                                textAlign = TextAlign.Start,
                                style = Typography.h1,
                                modifier = Modifier
                                    .weight(3f)
                            )
                            Text(
                                text = "10.01.22",
                                style = Typography.body1,
                            )
                            Text(
                                text = "549 $",
                                fontFamily = SemiBoldFont,
                                style = Typography.h5,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(end = 20.dp),
                                maxLines = 1
                            )
                        }
                        if (false) {
                            Text(
                                text = "Canceled",
                                textAlign = TextAlign.Start,
                                style = Typography.body2,
                            )
                        } else {
                            Text(
                                text = "Accepted",
                                textAlign = TextAlign.Start,
                                style = Typography.body2,
                            )
                        }
                    }
                }
            }
        }
    }

}