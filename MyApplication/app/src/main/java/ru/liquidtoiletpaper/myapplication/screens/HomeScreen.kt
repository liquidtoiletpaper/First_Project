package ru.liquidtoiletpaper.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.ProductItem
import ru.liquidtoiletpaper.myapplication.R
import ru.liquidtoiletpaper.myapplication.global.ProductsList
import ru.liquidtoiletpaper.myapplication.makeRequest
import ru.liquidtoiletpaper.myapplication.models.ProductsModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.productsListCheck
import ru.liquidtoiletpaper.myapplication.ui.theme.*

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
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
                Row(modifier = Modifier.fillMaxWidth()){
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = TextFieldBackground,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .padding(start = 5.dp)
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
                    if (searchText != "") {
                        IconButton(
                            modifier = Modifier.wrapContentHeight(),
                            onClick = {
                                searchText = ""
                                makeRequest(
                                    context,
                                    "https://tautaste.ru/search",
                                    mapOf("search" to searchText)
                                ) { response ->
                                    val shell = Json.decodeFromString<ResponseShell>(response.toString())
                                    if (shell.status == "success") {
                                        val searchModel = Json.decodeFromJsonElement<ProductsModel>(shell.content!!)
                                        for (i in searchModel.product) {
                                            val product = Product()
                                            product.productId = i.product_id
                                            product.image = i.image
                                            product.name = i.name
                                            product.description = i.description
                                            product.category = i.category
                                            product.cost = i.cost
                                            ProductsList.addProducts(product)
                                        }
                                    } else if (shell.code == 0) {

                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Back",
                                modifier = Modifier,
                                tint = PrimaryButton
                            )
                        }
                    }
                }
            }
        },
    ) { padding ->
        if(productsListCheck.value){
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 20.dp),
            ) {
                for (product in ProductsList.products) {
                    ProductItem(product = product, navController)
                }
            }
        } else {
            // Loading screen
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                GlideImage(
                    modifier = Modifier
                        .padding(top = 120.dp)
                        .size(150.dp),
                    model = R.drawable.downloadinggif,
                    contentDescription = "downloading",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}