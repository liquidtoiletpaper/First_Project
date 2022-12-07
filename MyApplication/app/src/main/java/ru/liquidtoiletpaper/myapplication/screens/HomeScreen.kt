package ru.liquidtoiletpaper.myapplication.screens

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.*
import ru.liquidtoiletpaper.myapplication.models.AuthModel
import ru.liquidtoiletpaper.myapplication.models.ProductModel
import ru.liquidtoiletpaper.myapplication.models.ProductsModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val navController = rememberNavController()
    var key = false
    fun validate(length: Int, minLength: Int, maxLength: Int) {
        if (length < minLength || length > maxLength) {
            key = true
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = DarkAppBarBackground,
                contentColor = Color.White,
                contentPadding = PaddingValues(vertical = 5.dp)
            ) {
                var isErrorSearch by rememberSaveable { mutableStateOf(false) }
                var searchText by remember { mutableStateOf("") }
                if(searchText != "") {
                    IconButton(
                        onClick = {
                            searchText = ""
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Back",
                            modifier = Modifier
                        )
                    }
                }
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = TextFieldBackground,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                        .padding(top = 5.dp)
                        .border(width = 1.dp, color = SecondaryButton, shape = Shapes.small),
                    singleLine = true,

                    value = searchText,
                    onValueChange = { searchText = it.take(128) },
                    placeholder = {
                        Image(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(PrimaryTextField)
                        )
                        Text(
                            "Search",
                            fontFamily = NormalFont,
                            modifier = Modifier
                                .padding(start = 30.dp)
                        )
                    },
                    shape = RoundedCornerShape(5.dp),
                    isError = isErrorSearch,
                    keyboardActions = KeyboardActions {
                        validate(
                            searchText.length,
                            6,
                            72
                        ); isErrorSearch = key
                    }
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
            .padding(padding)
        ) {
            requestProduct(2, context) { response ->
                val shell = Json.decodeFromString<ResponseShell>(response.toString())
                if(shell.status == "success") {
                    val productModel = Json.decodeFromJsonElement<ProductModel>(shell.content!!)
                    Product.product_id = productModel.product_id
                    Product.image = productModel.image
                    Product.name = productModel.name
                    Product.description = productModel.description
                    Product.category = productModel.category
                    Product.cost = productModel.cost
                    Log.d("MyLog", "Result: ${productModel.product_id}")
                }else {
                    Toast.makeText(context, "Что-то не так", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}