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
    //Font(R.font.oswald_medium, weight = FontWeight.Normal),
    //Font(R.font.oswald_light, weight = FontWeight.Thin)
    Font(R.font.jetbrainsmono_medium, weight = FontWeight.Normal),
    Font(R.font.jetbrainsmono_light, weight = FontWeight.Thin)
)
val SemiBoldFont = FontFamily(
    //Font(R.font.oswald_medium, weight = FontWeight.Normal)
    Font(R.font.jetbrainsmono_medium, weight = FontWeight.Normal)
)
val NormalFont = FontFamily(
    //Font(R.font.oswald_light, weight = FontWeight.Thin)
    Font(R.font.jetbrainsmono_light, weight = FontWeight.Thin)
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
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp,
        color = PrimaryWhite,
        letterSpacing = 0.5.sp,
    ),
    body2 = TextStyle(
        fontFamily = NormalFont,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp,
        color = SecondaryText,
        letterSpacing = 0.5.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = NormalFont,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp,
        color = DisabledText,
        letterSpacing = 0.5.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = NormalFont,
        fontWeight = FontWeight.Thin,
        fontSize = 14.sp,
        color = DisabledText,
        letterSpacing = 0.5.sp,
    ),
)