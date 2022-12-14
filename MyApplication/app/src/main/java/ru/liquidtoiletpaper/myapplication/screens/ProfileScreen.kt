package ru.liquidtoiletpaper.myapplication.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.liquidtoiletpaper.myapplication.StartActivity
import ru.liquidtoiletpaper.myapplication.global.User
import ru.liquidtoiletpaper.myapplication.screens.profileScreens.CitiesList
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun ProfileScreen(navController: NavHostController) {
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkAppBarBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 20.dp)
                    .wrapContentHeight(),
            ) {
                Text(
                    text = "${User.name} ${User.lastname}",
                    style = MaterialTheme.typography.h1,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
        ){
            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clickable {
                        navController.navigate("myPurchasesScreen")
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkAppBarBackground)
                        .padding(start = 20.dp, end = 10.dp)
                        .padding(vertical = 12.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(PrimaryWhite)
                        )
                        Text(
                            text = "My purchases",
                            maxLines = 1,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 20.dp)
                        )
                        Text(
                            text = "0",
                            maxLines = 1,
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Image(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(SecondaryText),
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clickable {
                        navController.navigate("cityScreen")
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkAppBarBackground)
                        .padding(start = 20.dp, end = 10.dp)
                        .padding(vertical = 12.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Default.Place,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(PrimaryWhite)
                        )
                        Text(
                            text = "Choose city",
                            maxLines = 1,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 20.dp)
                        )
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = CitiesList.getSelected(),
                                maxLines = 1,
                                style = MaterialTheme.typography.subtitle1,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                            )
                            Image(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(SecondaryText),
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clickable {
                        navController.navigate("reviewsScreen")
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkAppBarBackground)
                        .padding(start = 20.dp, end = 10.dp)
                        .padding(vertical = 12.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(PrimaryWhite)
                        )
                        Text(
                            text = "Reviews",
                            maxLines = 1,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 20.dp)
                        )
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Image(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(SecondaryText),
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clickable {
                        navController.navigate("ProfileInfoScreen")
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkAppBarBackground)
                        .padding(start = 20.dp, end = 10.dp)
                        .padding(vertical = 12.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(PrimaryWhite)
                        )
                        Text(
                            text = "Profile info",
                            maxLines = 1,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 20.dp)
                        )
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Image(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(SecondaryText),
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .clickable {
                        navController.navigate("helpScreen")
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkAppBarBackground)
                        .padding(start = 20.dp, end = 10.dp)
                        .padding(vertical = 12.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            imageVector = Icons.Default.Info,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(PrimaryWhite)
                        )
                        Text(
                            text = "Help",
                            maxLines = 1,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 20.dp)
                        )
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Image(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(SecondaryText),
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
            ){
                val openDialog = remember { mutableStateOf(false)  }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            openDialog.value = true
                        },
                ) {
                    if (openDialog.value) {
                        AlertDialog(
                            modifier = Modifier
                                .padding(bottom = 20.dp),
                            backgroundColor = DarkAppBarBackground,
                            shape = RoundedCornerShape(5.dp),
                            onDismissRequest = {
                                openDialog.value = false
                            },
                            title = {
                                Text(
                                    text = "Are you sure?",
                                    style = MaterialTheme.typography.h1,
                                )
                            },
                            text = {
                                Text(
                                    text = "After exiting your account you should login again",
                                    maxLines = 3,
                                    style = MaterialTheme.typography.subtitle1,
                                )
                            },
                            confirmButton = {
                                Text(
                                    modifier = Modifier
                                        .clickable {
                                            activity?.finish()
                                            StartActivity()
                                        }
                                        .padding(horizontal = 15.dp)
                                        .padding(vertical = 15.dp),
                                    text = "Exit",
                                    maxLines = 1,
                                    style = MaterialTheme.typography.body1,
                                )
                            },
                            dismissButton = {
                                Text(
                                    modifier = Modifier
                                        .clickable {
                                            openDialog.value = false
                                        }
                                        .padding(horizontal = 15.dp)
                                        .padding(vertical = 15.dp),
                                    text = "Cancel",
                                    maxLines = 1,
                                    style = MaterialTheme.typography.body1,
                                )
                            }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkAppBarBackground)
                            .padding(start = 20.dp, end = 10.dp)
                            .padding(vertical = 12.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(PrimaryWhite)
                            )
                            Text(
                                text = "Log out",
                                maxLines = 1,
                                style = MaterialTheme.typography.body1,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(start = 20.dp)
                            )
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                Image(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "",
                                    colorFilter = ColorFilter.tint(SecondaryText),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}