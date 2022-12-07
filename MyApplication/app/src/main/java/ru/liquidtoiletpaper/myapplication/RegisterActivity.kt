package ru.liquidtoiletpaper.myapplication

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.DatePicker
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.ui.theme.*
import java.util.*

class RegisterActivity : ComponentActivity() {
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
                ) {
                    val navController = rememberNavController()
                    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    RegisterPage1(navController = navController)
                }
            }
        }
    }
}

@Composable
fun RegisterPage1(navController: NavController){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(PrimaryPageBackground),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Enter your E-mail",
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.h1,
                    fontSize = 25.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "E-mail",
                    maxLines = 1,
                    color = SecondaryText,
                    style = MaterialTheme.typography.h1,
                    fontSize = 15.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .padding(horizontal = 20.dp)
                )
                var emailText by rememberSaveable { mutableStateOf("") }
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
                        cursorColor = Color.White,
                        disabledLabelColor = DisabledText,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = DisabledText
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    singleLine = true,
                    value = emailText,
                    onValueChange = { emailText = it.take(24) },
                    placeholder = {
                        Text(
                            text = "E-mail",
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
                            24
                        ); isErrorEmail = key
                    }
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
                        if("@" in emailText){
                            val fullEmail = emailText.split("@")
                            val firstEmail = fullEmail[0]
                            val secondEmail = fullEmail[1]
                            if("." in secondEmail && firstEmail.length >= 2){
                                val halfEmail = secondEmail.split(".")
                                val thirdEmail = halfEmail[0]
                                val fourthEmail = halfEmail[1]
                                if(thirdEmail == "gmail" || thirdEmail == "mail" || thirdEmail == "yandex"
                                    || thirdEmail == "inbox") {
                                    if(fourthEmail == "com" || fourthEmail == "ru"){
                                        User.email = emailText
                                        isErrorEmail = false
                                        navController.navigate("registerPage2")
                                    }else {
                                        isErrorEmail = true
                                    }
                                }else {
                                    isErrorEmail = true
                                }
                            }else {
                                isErrorEmail = true
                            }
                        }else {
                            isErrorEmail = true
                        }
                    },
                ) {
                    Text(
                        text = "Enter",
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



@Composable
fun RegisterPage2(navController: NavController){
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
                    text = "Register",
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.h1,
                    fontSize = 25.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Введите свои данные",
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
                var nameText by rememberSaveable { mutableStateOf("") }
                var isErrorName by rememberSaveable { mutableStateOf(false) }
                var key = false
                fun validate(length: Int, minLength: Int, maxLength: Int) {
                    if (length < minLength || length > maxLength) {
                        key = true
                    }
                }
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = TextFieldBackground,
                        cursorColor = Color.White,
                        disabledLabelColor = DisabledText,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = DisabledText
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    singleLine = true,
                    value = nameText,
                    onValueChange = { nameText = it.take(24) },
                    placeholder = {
                        Text(
                            text = "What's your name?",
                            fontFamily = NormalFont,
                            color = PrimaryTextField
                        )
                    },
                    shape = RoundedCornerShape(5.dp),
                    trailingIcon = {
                        if (isErrorName) {
                            Icon(Icons.Filled.Warning, "error", tint = ErrorColor)
                        }
                    },
                    isError = isErrorName,
                    keyboardActions = KeyboardActions {
                        validate(
                            nameText.length,
                            1,
                            24
                        ); isErrorName = key
                    }
                )
                var lastnameText by rememberSaveable { mutableStateOf("") }
                var isErrorLastname by rememberSaveable { mutableStateOf(false) }
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = TextFieldBackground,
                        cursorColor = Color.White,
                        disabledLabelColor = DisabledText,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = DisabledText
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    singleLine = true,
                    value = lastnameText,
                    onValueChange = { lastnameText = it.take(24) },
                    placeholder = {
                        Text(
                            text = "Lastname",
                            fontFamily = NormalFont,
                            color = PrimaryTextField
                        )
                    },
                    shape = RoundedCornerShape(5.dp),
                    trailingIcon = {
                        if (isErrorLastname) {
                            Icon(Icons.Filled.Warning, "error", tint = ErrorColor)
                        }
                    },
                    isError = isErrorLastname,
                    keyboardActions = KeyboardActions {
                        validate(
                            lastnameText.length,
                            1,
                            24
                        ); isErrorLastname = key
                    }
                )

                Box(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                ){
                    DropdownMenu()
                }

                var isErrorPassword by rememberSaveable { mutableStateOf(false) }
                var passwordText by remember { mutableStateOf("") }
                var passwordVisible by rememberSaveable { mutableStateOf(false) }
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = TextFieldBackground,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    singleLine = true,

                    value = passwordText,
                    onValueChange = { passwordText = it.take(32) },
                    placeholder = { Text("Password", fontFamily = NormalFont, color = PrimaryTextField) },
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
                            72
                        ); isErrorPassword = key
                    }
                )
                Text(
                    text = "Password must be from 4 to 72 char",
                    maxLines = 1,
                    color = SecondaryText,
                    style = MaterialTheme.typography.body1,
                    fontSize = 12.sp,
                    fontFamily = NormalFont,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 20.dp)
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
                        .padding(top = 10.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        if(nameText.isEmpty()){
                            isErrorName = true
                        }
                        if(lastnameText.isEmpty()){
                            isErrorLastname = true
                        }
                        if(passwordText.length < 4){
                            isErrorPassword = true
                        }
                        if(nameText.isNotEmpty() && lastnameText.isNotEmpty() && passwordText.length >= 4){
                            isErrorName = false
                            isErrorLastname = false
                            isErrorPassword = false
                            User.name = nameText
                            User.lastname = lastnameText
                            User.password = passwordText
                            navController.navigate("registerPage3")
                        }
                    },
                ) {
                    Text(
                        text = "Enter",
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



@Composable
fun RegisterPage3(navController: NavController){
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
                    text = "Enter your date of birth",
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.h1,
                    fontSize = 25.sp,
                    fontFamily = SemiBoldFont,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Date of birth",
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
                var dateText by rememberSaveable { mutableStateOf("") }
                var isErrorDate by rememberSaveable { mutableStateOf(false) }
                var key = false
                val mYear: Int
                val mMonth: Int
                val mDay: Int
                val mCalendar = Calendar.getInstance()

                mYear = mCalendar.get(Calendar.YEAR)
                mMonth = mCalendar.get(Calendar.MONTH)
                mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

                mCalendar.time = Date()

                val mDate = remember { mutableStateOf("") }
                val mDatePickerDialog = DatePickerDialog(
                    context,
                    { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                        mDate.value = "$mDayOfMonth.${mMonth+1}.$mYear"
                    }, mYear, mMonth, mDay
                )

                fun validate(year: Int, month: Int, day: Int) {
                    if (year <= (mYear - 18)) {
                        if (month <= mMonth){
                            if(day <= mDay){
                                key = true
                            }
                        }
                    }
                }
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = TextFieldBackground,
                        cursorColor = Color.White,
                        disabledLabelColor = DisabledText,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = DisabledText
                    ),

                    enabled = false,
                    singleLine = true,
                    value = mDate.value,
                    onValueChange = { dateText = it.take(10) },
                    placeholder = {
                        Text(
                            text = "01.01.1991",
                            fontFamily = NormalFont
                        )
                    },
                    shape = RoundedCornerShape(5.dp),
                    trailingIcon = {
                        if (isErrorDate) {
                            Icon(Icons.Filled.Warning, "error", tint = ErrorColor)
                        }
                    },
                    isError = isErrorDate,
                    keyboardActions = KeyboardActions {
                        validate(
                            Integer.parseInt(mDate.value.split(".")[2]),
                            Integer.parseInt(mDate.value.split(".")[1]),
                            Integer.parseInt(mDate.value.split(".")[0])
                        ); isErrorDate = key
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp)
                        .clickable {
                            mDatePickerDialog.show()
                        },
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
                        .padding(top = 25.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        makeRequest(context, "http:/tautaste.ru/reg", mapOf("email" to User.email, "name" to User.name, "lastname" to User.lastname, "password" to User.password)){ response ->
                            val shell = Json.decodeFromString<ResponseShell>(response.toString())
                            if(shell.status == "success") {
                                /*
                                val regModel = Json.decodeFromJsonElement<RegModel>(shell.content!!)
                                User.name = regModel.name
                                User.lastname = regModel.lastname
                                User.email = regModel.email
                                User.password = regModel.password
                                 */
                                context.startActivity(Intent(context, MainActivity::class.java))
                            }else if (shell.code == 0){
                                Toast.makeText(context, "Почта занята", Toast.LENGTH_SHORT).show()
                            }else if (shell.code == 1){
                                Toast.makeText(context, "Слишком короткий пароль", Toast.LENGTH_SHORT).show()
                            }else if (shell.code == 2){
                                Toast.makeText(context, "Заполните имя и фамилию", Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(context, "Что-то не так", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                ) {
                    Text(
                        text = "Create genshin impact account",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 15.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center,
                    )
                }
                Text(
                    text = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.",
                    maxLines = 5,
                    color = SecondaryText,
                    style = MaterialTheme.typography.body1,
                    fontSize = 11.sp,
                    fontFamily = NormalFont,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(horizontal = 20.dp)
                )
            }
        }
    }
}