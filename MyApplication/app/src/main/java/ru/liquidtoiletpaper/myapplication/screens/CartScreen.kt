package ru.liquidtoiletpaper.myapplication.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.*
import ru.liquidtoiletpaper.myapplication.global.CartList
import ru.liquidtoiletpaper.myapplication.global.FavId
import ru.liquidtoiletpaper.myapplication.global.ProdIds
import ru.liquidtoiletpaper.myapplication.global.ProductsList
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
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
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                    val temp = mutableListOf<Int>()
                    for(prod in ProductsList.products){
                        temp.add(prod.productId)
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
                                                    removeCartProducts(context, product.productId) {
                                                        CartList.removeProducts(product)
                                                        ProdIds.reduce(product.productId)
                                                    }
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
                                                addCartProducts(context, product.productId) {
                                                    CartList.addProducts(product)
                                                    ProdIds.amplify(product.productId)
                                                }
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
                                }
                                val openDialog = remember { mutableStateOf(false)  }
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
                                                text = "Удалить продукт с корзины?",
                                                style = MaterialTheme.typography.h1,
                                            )
                                        },
                                        text = {
                                            Text(
                                                text = "Вы можете снова добавить его позже",
                                                maxLines = 5,
                                                style = MaterialTheme.typography.subtitle1,
                                            )
                                        },
                                        confirmButton = {
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clickable {
                                                        removeAllCartProducts(context, product.productId){

                                                        }
                                                        CartList.removeAllProducts(product)
                                                        ProdIds.removeProducts(product.productId)
                                                        /*
                                                        CartList.clearProducts()
                                                        ProdIds.clearProducts()
                                                        requestCartProducts(context) { response ->
                                                            val shell = Json.decodeFromString<ResponseShell>(response.toString())
                                                            if (shell.status == "success") {
                                                                val cartProductModel = Json.decodeFromJsonElement<JsonArray>(shell.content!!)

                                                                for(i in cartProductModel){
                                                                    val p = ProductsList.products.find {
                                                                        it.productId == Integer.parseInt(i.toString())
                                                                    }
                                                                    if (p != null) {
                                                                        CartList.addProducts(p)
                                                                        if(ProdIds.products.contains(p.productId)) {
                                                                            ProdIds.amplify(p.productId)
                                                                        } else {
                                                                            ProdIds.addProducts(p.productId)
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                         */
                                                    }
                                                    .padding(horizontal = 15.dp)
                                                    .padding(vertical = 15.dp),
                                                text = "Удалить",
                                                color = ErrorColor,
                                                maxLines = 1,
                                                style = MaterialTheme.typography.body1,
                                            )
                                        },
                                        dismissButton = {
                                            Text(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clickable {
                                                        openDialog.value = false
                                                    }
                                                    .padding(horizontal = 15.dp)
                                                    .padding(vertical = 15.dp),
                                                text = "Отмена",
                                                maxLines = 1,
                                                style = MaterialTheme.typography.body1,
                                            )
                                        }
                                    )
                                }
                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = ErrorColor,
                                        contentColor = PrimaryWhite,
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp)
                                        .padding(horizontal = 20.dp),
                                    shape = RoundedCornerShape(5.dp),
                                    onClick = {
                                        openDialog.value = true
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Delete",
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