package ru.liquidtoiletpaper.myapplication.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import ru.liquidtoiletpaper.myapplication.MainActivity
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
                            imageVector = Icons.Filled.ArrowBack,
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
    ) {

    }
}