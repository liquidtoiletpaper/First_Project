package ru.liquidtoiletpaper.myapplication.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.ProductItem
import ru.liquidtoiletpaper.myapplication.global.CartList
import ru.liquidtoiletpaper.myapplication.global.FavId
import ru.liquidtoiletpaper.myapplication.global.ProdIds
import ru.liquidtoiletpaper.myapplication.global.ProductsList
import ru.liquidtoiletpaper.myapplication.productItem
import ru.liquidtoiletpaper.myapplication.ui.theme.*
@Composable
fun CartScreen(navController: NavHostController) {
    Log.d("MyLog", CartList.products.toString())
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
                        text = "Cart",
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
            Column(Modifier.fillMaxWidth()){
                Divider()
                Row(
                    modifier = Modifier
                        .background(DarkAppBarBackground)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .padding(start = 20.dp),
                            text = "Товаров: ${CartList.products.size}",
                            textAlign = TextAlign.Start,
                            style = Typography.body2,
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .padding(start = 20.dp),
                            text = "${CartList.calculateValue()} ₽",
                            textAlign = TextAlign.Start,
                            style = Typography.h1,
                        )
                    }
                    Column() {
                        val clicked = remember { mutableStateOf(true) }
                        var text = "Оплатить"
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
                                .padding(end = 20.dp),
                            shape = RoundedCornerShape(5.dp),
                            onClick = {

                            },
                        ) {
                            Text(
                                text = text,
                                color = PrimaryWhite,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
                Divider()
            }
        }
    ) { padding ->
        if(CartList.products.isEmpty()){
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Your cart is empty",
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.h1,
                    fontSize = 25.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Use catalog to choose products",
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
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
            ) {
                item {
                    val temp = mutableListOf<Int>()
                    for(prod in ProductsList.products){
                        temp.add(prod.productId)
                    }
                    for(p in CartList.products) {
                        Log.d("MyLog", p.productId.toString())
                    }
                    for(product in CartList.products) {
                        if(product.productId in temp) {
                            Log.d("MyLog", product.name)
                            Log.d("MyLog", "temp $temp")
                            fun calculateAmount(id: Int?): Int {
                                val amount = ProdIds.products.get(product.productId)
                                Log.d("MyLog", "amount $amount")
                                Log.d("MyLog", "amount id ${product.productId}")
                                return amount!!.toInt()
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                ProductsList.findProduct(product.productId)
                                    ?.let { ProductItem(product = it, navController) }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "Всего: ${ProdIds.calculateValue(product.productId)}",
                                        //text = "Всего: ${ProdIds.calculateValue(product.productId)}",
                                        style = Typography.h5,
                                        modifier = Modifier
                                            .weight(6f)
                                    )
                                    Row(
                                        modifier = Modifier
                                            .weight(3f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        IconButton(
                                            modifier = Modifier,
                                            onClick = {
                                                if(ProdIds.products[product.productId]!! > 1) {
                                                    CartList.removeProducts(product)
                                                    ProdIds.reduce(product.productId)
                                                }
                                            },
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowLeft,
                                                contentDescription = "",
                                                modifier = Modifier,
                                                tint = PrimaryWhite,
                                            )
                                        }
                                        Text(
                                            text = "${calculateAmount(product.productId)}",
                                            style = Typography.h5,
                                            modifier = Modifier
                                                .padding(end = 10.dp),
                                        )
                                        IconButton(
                                            modifier = Modifier,
                                            onClick = {
                                                CartList.addProducts(product)
                                                Log.d("MyLog", ProdIds.products.toString())
                                                ProdIds.amplify(product.productId)
                                            },
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowRight,
                                                contentDescription = "",
                                                modifier = Modifier,
                                                tint = PrimaryWhite,
                                            )
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .align(alignment = Alignment.CenterVertically)
                                    ) {
                                        IconButton(
                                            onClick = {
                                                CartList.removeAllProducts(product)
                                                ProdIds.removeProducts(product.productId)
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
                                        .padding(end = 5.dp)
                                        .weight(7f),
                                    shape = RoundedCornerShape(5.dp),
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            "Товар добавлен",
                                            Toast.LENGTH_SHORT
                                        )
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
                            }
                            Divider()
                            temp.remove(product.productId)
                            Log.d("MyLog", "temp 2 $temp")
                            Log.d("MyLog", product.productId.toString())
                        }
                    }
                }
            }
        }
    }
}