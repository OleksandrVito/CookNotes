package ua.vitolex.cooknotes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = PrimaryColor,
    primaryVariant = SecondaryColor,
    secondary = BorderColor.copy(0.7f),
    background = Color.White,
)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = SecondaryColor,
    secondary = BorderColor.copy(0.7f),
    background = Color.White,
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CookNotesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = DarkColorPalette.secondary,
            darkIcons = true
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = LightColorPalette.secondary,
            darkIcons = true
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}