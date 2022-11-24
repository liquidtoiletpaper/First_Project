package ru.liquidtoiletpaper.myapplication.screens.profileScreens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import ru.liquidtoiletpaper.myapplication.ui.theme.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import java.util.*

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
                    onClick = { navController.navigateUp() }
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
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(PrimaryPageBackground)
        ) {
            var nameText by rememberSaveable { mutableStateOf("Vladimir") }
            var isErrorName by rememberSaveable { mutableStateOf(false) }
            var key = false
            fun validate(length: Int, minLength: Int, maxLength: Int) {
                if (length < minLength || length > maxLength) {
                    key = true
                }
            }
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
                    .padding(vertical = 10.dp),
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
                    if(!isErrorName){
                        Text(
                            text = "Name",
                            fontFamily = NormalFont,
                            color = PrimaryTextField
                        )
                    } else {
                        Text(
                            text = "Name",
                            fontFamily = NormalFont,
                            color = ErrorColor
                        )
                    }
                }
            )
            BorderLine()

            var lastnameText by rememberSaveable { mutableStateOf("Zelenskiy") }
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
                    if(!isErrorLastname){
                        Text(
                            text = "Lastname",
                            fontFamily = NormalFont,
                            color = PrimaryTextField
                        )
                    } else {
                        Text(
                            text = "Lastname",
                            fontFamily = NormalFont,
                            color = ErrorColor
                        )
                    }
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
                    ){ DropdownMenu() }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp, end = 20.dp)
                    ){ MyDatePicker() }
                }
            }
            BorderLine()

            var emailText by rememberSaveable { mutableStateOf("zelenskiemail@mail.com") }
            var isErrorEmail by rememberSaveable { mutableStateOf(false) }
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
                    .padding(vertical = 10.dp),
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
                    if(!isErrorEmail){
                        Text(
                            text = "Email",
                            fontFamily = NormalFont,
                            color = PrimaryTextField
                        )
                    } else {
                        Text(
                            text = "Email",
                            fontFamily = NormalFont,
                            color = ErrorColor
                        )
                    }
                }
            )
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

@Composable
fun DropdownMenu(){
    var mExpanded by remember { mutableStateOf(false) }
    val mGender = listOf("Male", "Female")
    var mSelectedText by remember { mutableStateOf(mGender[0]) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        Modifier
            .padding(top = 10.dp)) {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = TextFieldBackground,
                cursorColor = Color.Black,
                disabledLabelColor = DisabledText,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = DisabledText
            ),
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            enabled = false,
            modifier = Modifier
                .clickable { mExpanded = !mExpanded }
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {
                Text(
                    text = "Gender",
                    fontFamily = NormalFont,
                    color = PrimaryTextField
                )
            },
            trailingIcon = {
                Icon(icon,"contentDescription",)
            }
        )
        MaterialTheme(
            colors = MaterialTheme.colors.copy(surface = TextFieldBackground),
            shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(5))
        ){
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                mGender.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            mSelectedText = label
                            mExpanded = false
                        },
                    ) {
                        Text(
                            text = label,
                        )
                    }
                }
            }
        }
    }
}