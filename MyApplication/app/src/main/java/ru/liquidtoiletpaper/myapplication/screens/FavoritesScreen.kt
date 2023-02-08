package ru.liquidtoiletpaper.myapplication.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.*
import ru.liquidtoiletpaper.myapplication.global.FavId
import ru.liquidtoiletpaper.myapplication.global.ProductsList
import ru.liquidtoiletpaper.myapplication.models.CharModel
import ru.liquidtoiletpaper.myapplication.models.FavProductsModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun FavoritesScreen(navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = DarkAppBarBackground,
                contentColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Избранное",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 16.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        bottomBar = {

        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
        ) {
            item {
                for (id in FavId.ids) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        ProductsList.findProduct(id)
                            ?.let { ProductItem(product = it, navController) }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            val clicked = remember { mutableStateOf(true) }
                            Button(
                                enabled = clicked.value,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = PrimaryButton,
                                    contentColor = PrimaryWhite,
                                    disabledBackgroundColor = SecondaryButton,
                                    disabledContentColor = PrimaryWhite
                                ),
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .padding(end = 20.dp)
                                    .weight(7f),
                                shape = RoundedCornerShape(5.dp),
                                onClick = {
                                    Toast.makeText(context, "Товар добавлен", Toast.LENGTH_SHORT)
                                        .show()
                                    clicked.value = false
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.ShoppingCart,
                                    contentDescription = "Fav",
                                    modifier = Modifier,
                                    tint = PrimaryWhite,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .align(alignment = CenterVertically)
                            ) {
                                IconButton(
                                    onClick = {
                                        removeFavProducts(context, id){
                                            FavId.clearProducts()
                                            requestFavProducts(context) { response ->
                                                val shell = Json.decodeFromString<ResponseShell>(response.toString())
                                                if (shell.status == "success") {
                                                    val favProductModel = Json.decodeFromJsonElement<JsonArray>(shell.content!!)
                                                    for(i in favProductModel){
                                                        FavId.addProducts(Integer.parseInt(i.toString()))
                                                    }
                                                }
                                            }
                                        }
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Fav",
                                        modifier = Modifier,
                                        tint = FavColor,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}