package ru.liquidtoiletpaper.myapplication.screens.profileScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.screens.ItemOrder
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun MyPurchases(navController: NavHostController){
    val check = true
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
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier,
                        tint = PrimaryWhite,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Purchases",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                    )
                    IconButton(
                        onClick = {  },
                        enabled = false
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .alpha(0f),
                            tint = PrimaryWhite,
                        )
                    }
                }

            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth()
                .background(PrimaryPageBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(!check){
                Text(
                    text = "You have no purchases",
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.h1,
                    fontSize = 25.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Choose products from catalog",
                    maxLines = 2,
                    color = LowerPrimaryText,
                    style = MaterialTheme.typography.body1,
                    fontSize = 14.sp,
                    fontFamily = NormalFont,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 8.dp)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryButton,
                        contentColor = PrimaryWhite,
                        disabledBackgroundColor = SecondaryButton,
                        disabledContentColor = PrimaryWhite
                    ),
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        navController.navigate("catalogScreen")
                    },
                ) {
                    Text(
                        text = "To catalog",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 15.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                ) {
                    ItemOrder(navController = navController)
                    /*
                    for (i in 0..4) {
                        /*
                        val purchaseInfo = listOf(){
                            val purchaseStatus = i.status
                            val purchaseIndex = i.index
                            val purchaseDate = i.date
                            val purchaseValue = i.value
                        }
                         */
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
                                    if (!i.status) {
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
                                            text = "№ ${i.index}",
                                            textAlign = TextAlign.Start,
                                            fontFamily = SemiBoldFont,
                                            style = MaterialTheme.typography.h1,
                                            fontSize = 14.sp,
                                            color = PrimaryWhite,
                                            letterSpacing = 0.5.sp,
                                            modifier = Modifier
                                                .weight(3f)
                                        )
                                        Text(
                                            text = i.date,
                                            fontFamily = NormalFont,
                                            style = MaterialTheme.typography.body1,
                                            fontSize = 10.sp,
                                            color = SecondaryText,
                                        )
                                        Text(
                                            text = "${i.value} $",
                                            fontFamily = SemiBoldFont,
                                            style = MaterialTheme.typography.h1,
                                            fontSize = 12.sp,
                                            color = PrimaryWhite,
                                            textAlign = TextAlign.End,
                                            modifier = Modifier
                                                .weight(2f)
                                                .padding(end = 20.dp),
                                            letterSpacing = 1.sp,
                                            maxLines = 1
                                        )
                                    }
                                    if (!i.status) {
                                        Text(
                                            text = "Canceled",
                                            textAlign = TextAlign.Start,
                                            fontFamily = NormalFont,
                                            style = MaterialTheme.typography.body1,
                                            fontSize = 10.sp,
                                            color = DisabledText
                                        )
                                    } else {
                                        Text(
                                            text = "Accepted",
                                            textAlign = TextAlign.Start,
                                            fontFamily = NormalFont,
                                            style = MaterialTheme.typography.body1,
                                            fontSize = 10.sp,
                                            color = DisabledText
                                        )
                                    }
                                }
                            }
                        }
                        BorderLine()
                    }

                     */
                }
            }
        }
    }
}