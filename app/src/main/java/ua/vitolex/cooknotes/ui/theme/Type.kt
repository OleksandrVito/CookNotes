package ua.vitolex.cooknotes.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ua.vitolex.cooknotes.R

// Set of Material typography styles to start with

val montserratFF = FontFamily(
    Font(
        R.font.montserrat_medium,
        weight = FontWeight.Medium
    ),
    Font(
        R.font.montserrat_regular,
        weight = FontWeight.Normal
    ),
    Font(
        R.font.montserrat_ligth,
        weight = FontWeight.Light
    )
)
val pacifico = FontFamily(
    Font(
        R.font.pacifico_regular,
        weight = FontWeight.Bold
    ),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = montserratFF,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = TextColor,
    ),
    subtitle1 = TextStyle(
        fontFamily = montserratFF,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp,
        color = Color.Gray
    ),
    h2 = TextStyle(
        fontFamily = montserratFF,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = pacifico,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
)