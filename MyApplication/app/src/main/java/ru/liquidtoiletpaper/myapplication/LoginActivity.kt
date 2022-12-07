package ru.liquidtoiletpaper.myapplication

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.models.AuthModel
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.ui.theme.*

class LoginActivity : ComponentActivity() {

    // Заблокировать поворот экрана на устройстве
    @Composable
    fun LockScreenOrientation(orientation: Int) {
        val context = LocalContext.current
        DisposableEffect(Unit) {
            val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
            val originalOrientation = requestedOrientation
            requestedOrientation = orientation
            onDispose {
                requestedOrientation = originalOrientation
            }
        }
    }
    fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    val navController = rememberNavController()
                    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    LoginPage(navController)
                }
            }
        }
    }
}

@Composable
fun LoginPage(navController: NavController){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(PrimaryPageBackground),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(horizontal = 20.dp),
                        contentColorFor(backgroundColor = PrimaryWhite),
                    )
                }
            }
            // Колонна с кнопками
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome back!",
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.h1,
                    fontSize = 25.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur",
                    maxLines = 2,
                    color = SecondaryText,
                    style = MaterialTheme.typography.body1,
                    fontSize = 12.sp,
                    fontFamily = NormalFont,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 8.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Enter account information",
                    maxLines = 1,
                    color = SecondaryText,
                    style = MaterialTheme.typography.h1,
                    fontSize = 15.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .padding(horizontal = 20.dp)
                )
                var emailText by rememberSaveable { mutableStateOf("email2@yandex.ru") }
                var isErrorEmail by rememberSaveable { mutableStateOf(false) }
                var key = false
                fun validate(length: Int, minLength: Int, maxLength: Int) {
                    if (length < minLength || length > maxLength) {
                        key = true
                    }
                }
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = TextFieldBackground,
                        cursorColor = androidx.compose.ui.graphics.Color.Black,
                        disabledLabelColor = DisabledText,
                        focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                        unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                        focusedLabelColor = DisabledText,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    singleLine = true,
                    value = emailText,
                    onValueChange = { emailText = it.take(64) },
                    placeholder = {
                        Text(
                            text = "Email",
                            fontFamily = NormalFont,
                            color = PrimaryTextField
                        )
                            },
                    shape = RoundedCornerShape(5.dp),
                    trailingIcon = {
                        if (isErrorEmail) {
                            Icon(Icons.Filled.Warning, "error", tint = ErrorColor)
                        }
                    },
                    isError = isErrorEmail,
                    keyboardActions = KeyboardActions {
                        validate(
                            emailText.length,
                            1,
                            64
                        ); isErrorEmail = key
                    }
                )


                var isErrorPassword by rememberSaveable { mutableStateOf(false) }
                var passwordText by remember { mutableStateOf("1234") }
                var passwordVisible by rememberSaveable { mutableStateOf(false) }
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = TextFieldBackground,
                        cursorColor = androidx.compose.ui.graphics.Color.White,
                        focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                        unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    singleLine = true,

                    value = passwordText,
                    onValueChange = { passwordText = it.take(64) },
                    placeholder = {
                        Text(
                            "Password",
                            fontFamily = NormalFont,
                            color = PrimaryTextField
                        )
                    },
                    shape = RoundedCornerShape(5.dp),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.KeyboardArrowUp
                        else Icons.Filled.KeyboardArrowDown

                        // Please provide localized description for accessibility services
                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = {passwordVisible = !passwordVisible}){
                            Icon(imageVector  = image, description)
                        }
                    },
                    /*
                    trailingIcon = {
                        if (isErrorPassword) {
                            Icon(Icons.Filled.Warning, "error", tint = Error)
                        }
                    },
                     */
                    isError = isErrorPassword,
                    keyboardActions = KeyboardActions {
                        validate(
                            passwordText.length,
                            4,
                            64
                        ); isErrorPassword = key
                    }
                )
                Text(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp)
                        .clickable(enabled = true) {
                            context.startActivity(Intent(context, MainActivity::class.java))
                        },
                    textAlign = TextAlign.End,
                    text = "Forgot password?",
                    fontFamily = NormalFont,
                    color = LinkText,
                    fontSize = 12.sp,
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryButton,
                        contentColor = PrimaryWhite,
                        disabledBackgroundColor = SecondaryButton,
                        disabledContentColor = PrimaryWhite
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        makeRequest(context, "http:/tautaste.ru/auth", mapOf("email" to emailText, "password" to passwordText)){ response ->
                            val shell = Json.decodeFromString<ResponseShell>(response.toString())
                            if(shell.status == "success") {
                                val authModel = Json.decodeFromJsonElement<AuthModel>(shell.content!!)
                                User.id = authModel.id
                                User.email = authModel.email
                                User.name = authModel.name
                                User.lastname = authModel.lastname
                                User.gender = authModel.gender
                                context.startActivity(Intent(context, MainActivity::class.java))
                            }else if (shell.code == 0){
                                isErrorEmail = true
                                isErrorPassword = true
                                Toast.makeText(context, "Данные введены неверно", Toast.LENGTH_SHORT).show()
                            }else {
                                isErrorEmail = true
                                isErrorPassword = true
                                Toast.makeText(context, "Что-то не так", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                ) {
                    Text(
                        text = "Login",
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
}