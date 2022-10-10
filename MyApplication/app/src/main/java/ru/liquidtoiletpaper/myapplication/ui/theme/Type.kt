package ru.liquidtoiletpaper.myapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.liquidtoiletpaper.myapplication.R

val fonts = FontFamily(
    Font(R.font.montserrat_semibold, weight = FontWeight.SemiBold),
    Font(R.font.montserrat_medium, weight = FontWeight.Normal)
)
val SemiBoldFont = FontFamily(
    Font(R.font.montserrat_semibold, weight = FontWeight.SemiBold)
)
val NormalFont = FontFamily(
    Font(R.font.montserrat_medium, weight = FontWeight.Normal)
)


val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp
    ),
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = Color(0xffa4b0be)
    )
)