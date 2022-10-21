package ru.liquidtoiletpaper.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.AddItem
import ru.liquidtoiletpaper.myapplication.BottomBarScreen
import ru.liquidtoiletpaper.myapplication.BottomNavGraph
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun CartScreen() {
    val context = LocalContext.current
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your cart is empty",
                color = PrimaryText,
                style = MaterialTheme.typography.h1,
                fontSize = 25.sp,
                fontFamily = SemiBoldFont,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Use catalog to choose products",
                maxLines = 2,
                color = PrimaryText,
                style = MaterialTheme.typography.body1,
                fontSize = 20.sp,
                fontFamily = NormalFont,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 8.dp)
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = PrimaryButton,
                    contentColor = PrimaryText,
                    disabledBackgroundColor = SecondaryButton,
                    disabledContentColor = PrimaryText
                ),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    
                },
            ) {
                Text(
                    text = "To catalog",
                    color = PrimaryText,
                    style = MaterialTheme.typography.h1,
                    fontSize = 15.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}