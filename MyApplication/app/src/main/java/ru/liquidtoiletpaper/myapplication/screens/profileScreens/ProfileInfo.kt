package ru.liquidtoiletpaper.myapplication.screens.profileScreens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.liquidtoiletpaper.myapplication.ui.theme.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import ru.liquidtoiletpaper.myapplication.global.User
import ru.liquidtoiletpaper.myapplication.makeRequest
import ru.liquidtoiletpaper.myapplication.models.ResponseShell
import ru.liquidtoiletpaper.myapplication.models.UpdateModel
import java.util.*
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds


private val updated = mutableStateOf(false)

@Composable
fun ProfileInfo(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .background(PrimaryPageBackground),
        topBar = {
            TopAppBar(
                backgroundColor = DarkAppBarBackground,
                contentColor = Color.White,
            ) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                        updated.value = false
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier,
                        tint = PrimaryWhite,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Profile info",
                        color = PrimaryWhite,
                        style = MaterialTheme.typography.h1,
                        fontSize = 16.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        },
    ) { padding ->
        var ticks by remember { mutableStateOf(0) }
        LaunchedEffect(Unit) {
            while(true) {
                delay(3.seconds)
                ticks++
            }
        }
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(PrimaryPageBackground)
                .verticalScroll(rememberScrollState())
        ) {
            var errorCode = 0
            var nameText by rememberSaveable { mutableStateOf(User.name) }
            var isErrorName by rememberSaveable { mutableStateOf(false) }
            var key = false
            fun validate(length: Int, minLength: Int, maxLength: Int) {
                if (length < minLength || length > maxLength) {
                    key = true
                }
            }
            Text(
                text = "Личные данные",
                maxLines = 1,
                color = SecondaryText,
                style = MaterialTheme.typography.h1,
                fontSize = 15.sp,
                fontFamily = SemiBoldFont,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .padding(horizontal = 30.dp)
            )
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = TextFieldBackground,
                    cursorColor = Color.Gray,
                    disabledLabelColor = DisabledText,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = PrimaryWhite,
                    textColor = PrimaryWhite,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
                singleLine = true,
                value = nameText,
                onValueChange = { nameText = it.take(24) },
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
                },
                label = {
                    Text(
                        text = "Имя",
                        fontFamily = NormalFont,
                        color = if(!isErrorName) { PrimaryTextField } else { ErrorColor }
                    )
                }
            )

            var lastnameText by rememberSaveable { mutableStateOf(User.lastname) }
            var isErrorLastname by rememberSaveable { mutableStateOf(false) }
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = TextFieldBackground,
                    cursorColor = Color.Gray,
                    disabledLabelColor = DisabledText,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = DisabledText,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 10.dp),
                singleLine = true,
                value = lastnameText,
                onValueChange = { lastnameText = it.take(24) },
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
                },
                label = {
                    Text(
                        text = "Фамилия",
                        fontFamily = NormalFont,
                        color = if(!isErrorLastname) { PrimaryTextField } else { ErrorColor }
                    )
                }
            )
            BorderLine()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 20.dp, end = 10.dp)
                    ){ ru.liquidtoiletpaper.myapplication.DropdownMenu() }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp, end = 20.dp)
                    ){ MyDatePicker() }
                }
            }
            BorderLine()

            var emailText by rememberSaveable { mutableStateOf(User.email) }
            var isErrorEmail by rememberSaveable { mutableStateOf(false) }
            TextField(
                enabled = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = TextFieldBackground,
                    cursorColor = Color.Gray,
                    disabledLabelColor = DisabledText,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = PrimaryWhite,
                    textColor = PrimaryWhite,
                    disabledTextColor = DisabledText
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
                singleLine = true,
                value = emailText,
                onValueChange = { emailText = it.take(24) },
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
                },
                label = {
                    Text(
                        text = "Email",
                        fontFamily = NormalFont,
                        color = if(!isErrorEmail) { PrimaryTextField } else { ErrorColor }
                    )
                },
            )
            if(isErrorEmail){
                if(errorCode == 0){
                    Text(
                        text = "Введите почту",
                        maxLines = 1,
                        color = ErrorColor,
                        style = MaterialTheme.typography.body1,
                        fontSize = 13.sp,
                        fontFamily = NormalFont,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .padding(top = 5.dp)
                            .padding(bottom = 5.dp)
                    )
                }
            }
            var check = false
            var alpha = 0.5f
            if((nameText != User.name) || (lastnameText != User.lastname)  || (emailText != User.email)) {
                check = true
                alpha = 1f
            }
            Column(horizontalAlignment = Alignment.End){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryButton,
                        contentColor = PrimaryWhite,
                        disabledBackgroundColor = PrimaryButton.copy(alpha),
                        disabledContentColor = PrimaryWhite,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                        if (nameText.isEmpty()) {
                            isErrorName = true
                        }
                        if (lastnameText.isEmpty()) {
                            isErrorLastname = true
                        }
                        if (emailText.isEmpty()) {
                            isErrorEmail = true
                        }
                        if (nameText.isNotEmpty() && lastnameText.isNotEmpty() && emailText.isNotEmpty()) {
                            isErrorName = false
                            isErrorLastname = false
                            isErrorEmail = false
                            makeRequest(
                                context, "http:/tautaste.ru/updateUser",
                                mapOf(
                                    "id" to User.id.toString(),
                                    "name" to nameText,
                                    "lastname" to lastnameText,
                                    "gender" to User.gender,
                                    "email" to emailText
                                )
                            ) { response ->
                                val shell = Json.decodeFromString<ResponseShell>(response.toString())
                                if (shell.status == "success") {
                                    val updateModel = Json.decodeFromJsonElement<UpdateModel>(shell.content!!)
                                    User.email = updateModel.email
                                    User.location = updateModel.location
                                    User.name = updateModel.name
                                    User.lastname = updateModel.lastname
                                    User.gender = updateModel.gender
                                    updated.value = true
                                } else if (shell.code == 0) {
                                    errorCode = 0
                                    isErrorEmail = true
                                } else if (shell.code == 1) {
                                    errorCode = 1
                                    if (nameText.isEmpty()) {
                                        isErrorName = true
                                    }
                                    if (lastnameText.isEmpty()) {
                                        isErrorLastname = true
                                    }
                                }
                            }
                        }
                    },
                    enabled = check,
                ) {
                    Text(
                        text = "Применить изменения",
                        color = PrimaryWhite.copy(alpha),
                        style = MaterialTheme.typography.h1,
                        fontSize = 15.sp,
                        fontFamily = SemiBoldFont,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if(updated.value){
                SlideFromTopAnimation(2500, check = updated) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(PrimaryGreen)
                            .padding(vertical = 10.dp)
                            .padding(horizontal = 20.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Refreshed",
                            modifier = Modifier
                                .height(20.dp),
                            tint = PrimaryWhite,
                        )
                        Text(
                            text = "Данные обновлены",
                            maxLines = 1,
                            color = PrimaryWhite,
                            style = MaterialTheme.typography.h1,
                            fontSize = 15.sp,
                            fontFamily = SemiBoldFont,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BorderLine() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 2.dp)
            .border(width = 2.dp, color = PrimaryBorder)
    ) {

    }
}

@Composable
fun MyDatePicker() {
    val context = LocalContext.current
    var dateText by rememberSaveable { mutableStateOf("") }
    var isErrorDate by rememberSaveable { mutableStateOf(false) }
    var key = false
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    var mExpanded by remember { mutableStateOf(false) }
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

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
            cursorColor = androidx.compose.ui.graphics.Color.Black,
            disabledLabelColor = DisabledText,
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
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
        /*
        trailingIcon = {
            Icon(icon,"contentDescription",)
        },
         */
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
            .padding(top = 10.dp)
            .clickable {
                mDatePickerDialog.show()
            },
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SlideFromTopAnimation(time: Int, check: MutableState<Boolean>? = null, content: @Composable () -> Unit) {
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 200),
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically(
            animationSpec = spring(stiffness = Spring.StiffnessHigh),
        ) + fadeOut(),
        content = content,
        initiallyVisible = false //true - без анимации, false - с анимацией
    )

    LaunchedEffect(Unit) {
        delay(time.milliseconds)
        visible = false
        if(check!!.value){
            delay(200)
            check.value = false
        }
    }

}