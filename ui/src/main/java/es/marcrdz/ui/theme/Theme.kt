/*
 * Copyright (c) 2024.  All credits and comments to marcos.rdgz.dz@gmail.com
 */

package es.marcrdz.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = PokeRed800,
    onPrimary = PokeGray200,
    primaryContainer = PokeGray600,
    onPrimaryContainer = PokeRed900,
    inversePrimary = PokeRed400,
    secondary = PokeBlue100,
    secondaryContainer = PokeBlue50,
    tertiary = PokeYellow50,
    outline = PokeRed800,
    surface = PokeGray400,
    background = PokeGray900
)

private val LightColorPalette = lightColorScheme(
    primary = PokeRed400,
    onPrimary = Color.White,
    primaryContainer = PokeGray200,
    onPrimaryContainer = PokeRed600,
    inversePrimary = PokeRed800,
    secondary = PokeBlue900,
    secondaryContainer = PokeBlue800,
    tertiary = PokeYellow400,
    outline = PokeRed400,
    surface = PokeGray200,
    background = PokeGray50

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
fun PokomposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}