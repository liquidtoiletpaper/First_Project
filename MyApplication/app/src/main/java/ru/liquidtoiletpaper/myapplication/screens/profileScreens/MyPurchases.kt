package ru.liquidtoiletpaper.myapplication.screens.profileScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun MyPurchases(navController: NavController){
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = DarkAppBarBackground,
                contentColor = Color.White,

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Purchases",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 16.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center
                    )
                }

            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
    }
}