package ru.liquidtoiletpaper.myapplication.screens.profileScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun City(navController: NavHostController) {
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
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "City",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                    )
                    IconButton(
                        onClick = {  },
                        enabled = false
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
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
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 10.dp),
            ){
                val cities = CitiesList.cities
                Column(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                color = Color.Black
                            ),
                            onClick = { },
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 5.dp),
                            verticalArrangement = Arrangement.Center
                        ) {

                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = CitiesList.getSelected(),
                                    fontFamily = SemiBoldFont,
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 16.sp,
                                    color = PrimaryWhite,
                                    modifier = Modifier
                                        .weight(2f)
                                        .fillMaxWidth(),
                                    letterSpacing = 1.sp,
                                    maxLines = 1
                                )
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Selected",
                                    tint = PrimarySelected,
                                    modifier = Modifier
                                        .padding(end = 20.dp)
                                )
                            }
                        }
                    }
                    // BORDERLINE
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 2.dp)
                            .border(width = 2.dp, color = PrimaryBorder)
                    ) {

                    }
                }
                for (j in cities) {
                    if(!j.selected){
                        Column(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(
                                        color = Color.Black
                                    ),
                                    onClick = {
                                        for (k in cities) {
                                            if (k.city == CitiesList.getSelected()) {
                                                k.selected = false
                                            }
                                        }
                                        j.selected = true
                                        navController.navigateUp()
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
                                        .padding(start = 20.dp, top = 5.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {

                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = j.city,
                                            fontFamily = NormalFont,
                                            style = MaterialTheme.typography.body1,
                                            fontSize = 16.sp,
                                            color = PrimaryWhite,
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            letterSpacing = 1.sp,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                            // BORDERLINE
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height = 2.dp)
                                    .border(width = 2.dp, color = PrimaryBorder)
                            ) {

                            }
                        }
                    }
                }

            }
        }
    }
}

