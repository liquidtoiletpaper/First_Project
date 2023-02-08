package ru.liquidtoiletpaper.myapplication.screens.profileScreens

import android.graphics.drawable.Drawable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.liquidtoiletpaper.myapplication.ProductItem
import ru.liquidtoiletpaper.myapplication.R
import ru.liquidtoiletpaper.myapplication.global.CartList
import ru.liquidtoiletpaper.myapplication.global.User
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun MyPurchasesOrder(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .background(PrimaryPageBackground),
        topBar = {
            TopAppBar(
                backgroundColor = DarkAppBarBackground,
                contentColor = Color.White,
            ) {
                IconButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",
                        modifier = Modifier,
                        tint = PrimaryWhite,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 40.dp),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Order № 1",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 18.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp,
                        maxLines = 1
                    )
                }
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth()
                .background(PrimaryPageBackground)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(true) {
                        Icon(
                            modifier = Modifier
                                .size(80.dp),
                            painter = painterResource(id = R.drawable.icon_cancel),
                            contentDescription = "Canceled",
                            tint = ErrorColor,
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 15.dp),
                            text = "№ 1111",
                            color = PrimaryWhite,
                            style = MaterialTheme.typography.h1,
                            fontSize = 22.sp,
                            fontFamily = SemiBoldFont,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp),
                            text = "Canceled",
                            color = SecondaryText,
                            style = MaterialTheme.typography.body1,
                            fontSize = 12.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.5.sp
                        )
                    } else {
                        Icon(
                            modifier = Modifier
                                .size(80.dp),
                            painter = painterResource(id = R.drawable.icon_accept),
                            contentDescription = "Accepted",
                            tint = PrimaryGreen,
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 15.dp),
                            text = "№ 1111",
                            color = PrimaryWhite,
                            style = MaterialTheme.typography.h1,
                            fontSize = 22.sp,
                            fontFamily = SemiBoldFont,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 5.dp),
                            text = "Accepted",
                            color = SecondaryText,
                            style = MaterialTheme.typography.body1,
                            fontSize = 12.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
                for(product in CartList.products){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .clickable { }
                    ) {
                        ProductItem(product = product, navController = navController)
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                ){
                    Text(
                        text = "Покупатель",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 20.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Start,
                        letterSpacing = 0.5.sp,
                        maxLines = 1
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = "Имя",
                            color = SecondaryText,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 1
                        )
                        Text(
                            // user name
                            modifier = Modifier
                                .weight(2f),
                            text = "${User.name} ${User.lastname}",
                            color = PrimaryWhite,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 2
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = "Email",
                            color = SecondaryText,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 1
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f),
                            text = User.email,
                            color = PrimaryWhite,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 3
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                ){
                    Text(
                        text = "Оплата",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 20.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Start,
                        letterSpacing = 0.5.sp,
                        maxLines = 1
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = "Статус",
                            color = SecondaryText,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 1
                        )
                        if(true){
                            Text(
                                modifier = Modifier
                                    .weight(2f),
                                text = "Canceled",
                                color = PrimaryWhite,
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp,
                                fontFamily = NormalFont,
                                textAlign = TextAlign.Start,
                                letterSpacing = 0.5.sp,
                                maxLines = 2
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .weight(2f),
                                text = "Accepted",
                                color = PrimaryWhite,
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp,
                                fontFamily = NormalFont,
                                textAlign = TextAlign.Start,
                                letterSpacing = 0.5.sp,
                                maxLines = 2
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = "Товары",
                            color = SecondaryText,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 1
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f),
                            text = "${CartList.calculateValue()}",
                            color = PrimaryWhite,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 3
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f),
                            text = "Итого",
                            color = SecondaryText,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 1
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f),
                            text = "${CartList.calculateValue()}",
                            color = PrimaryWhite,
                            style = MaterialTheme.typography.body1,
                            fontSize = 14.sp,
                            fontFamily = NormalFont,
                            textAlign = TextAlign.Start,
                            letterSpacing = 0.5.sp,
                            maxLines = 3
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(bottom = 80.dp)
            ) {

            }
        }
    }
}