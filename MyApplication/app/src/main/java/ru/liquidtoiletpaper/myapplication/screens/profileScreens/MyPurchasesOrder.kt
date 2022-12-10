package ru.liquidtoiletpaper.myapplication.screens.profileScreens

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
import ru.liquidtoiletpaper.myapplication.R
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
            /*
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
                    if(!purchases[0].status) {
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
                            text = "№ ${purchases[0].index}",
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
                            text = "№ ${purchases[0].index}",
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
                for(i in products.indices){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .clickable { }
                    ) {
                            Image(
                                modifier = Modifier
                                    .weight(3f)
                                    .align(alignment = CenterVertically),
                                // product image
                                painter = painterResource(id = products[i].image),
                                contentDescription = "purchase image",
                                contentScale = ContentScale.Fit,
                            )

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
                                text = products[i].name,
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
                                text = products[i].description,
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
                                text = "${products[i].cost} $",
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
                            text = "Tom Angelo",
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
                            // user name
                            modifier = Modifier
                                .weight(2f),
                            text = "angelotommy@mail.com",
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
                        if(!purchases[0].status){
                            Text(
                                // user name
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
                                // user name
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
                            // user name
                            modifier = Modifier
                                .weight(2f),
                            text = "${purchases[0].value} $",
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
                            // user name
                            modifier = Modifier
                                .weight(2f),
                            text = "${purchases[0].value} $",
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
            */
        }
    }
}