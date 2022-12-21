package ru.liquidtoiletpaper.myapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.liquidtoiletpaper.myapplication.R
const val light = R.font.clearsans_regular
const val regular = R.font.clearsans_medium
val fonts = FontFamily(
    Font(regular, weight = FontWeight.SemiBold),
    Font(light, weight = FontWeight.Normal)
)
val SemiBoldFont = FontFamily(
    Font(regular, weight = FontWeight.SemiBold)
)
val NormalFont = FontFamily(
    Font(light, weight = FontWeight.Normal)
)


val Typography = Typography(
    h1 = TextStyle(
        fontFamily = SemiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    h2 = TextStyle(
        fontFamily = SemiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = SecondaryText,
        letterSpacing = 0.5.sp,
    ),
    h3 = TextStyle(
        fontFamily = SemiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    h4 = TextStyle(
        fontFamily = SemiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    h5 = TextStyle(
        fontFamily = SemiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    h6 = TextStyle(
        fontFamily = SemiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = SecondaryText,
        letterSpacing = 0.5.sp,
    ),
    body1 = TextStyle(
        fontFamily = NormalFont,
        fontSize = 16.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    body2 = TextStyle(
        fontFamily = NormalFont,
        fontSize = 16.sp,
        color = SecondaryText,
        letterSpacing = 0.5.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = NormalFont,
        fontSize = 16.sp,
        color = DisabledText,
        letterSpacing = 0.5.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = NormalFont,
        fontSize = 14.sp,
        color = DisabledText,
        letterSpacing = 0.5.sp,
    ),
)

val HugeTypography = Typography(
    h1 = TextStyle(
        fontFamily = SemiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 26.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    h2 = TextStyle(
        fontFamily = SemiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    body1 = TextStyle(
        fontFamily = NormalFont,
        fontSize = 18.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    body2 = TextStyle(
        fontFamily = NormalFont,
        fontSize = 18.sp,
        color = SecondaryText,
        letterSpacing = 0.5.sp,
    ),
)