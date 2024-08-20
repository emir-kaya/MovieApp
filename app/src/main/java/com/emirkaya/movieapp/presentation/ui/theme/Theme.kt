package com.emirkaya.movieapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val PrimaryColor = Color(0xFF2f3d53)
val SecondaryColor = Color(0xFF094044)
val OnSecondaryColor = Color(0xFF072d3a)
val BackgroundColor = Color(0xFF091d26)
val SurfaceColor = Color(0xFFd6c4b0)
val AccentColor = Color(0xFFf84f2a)
val TextColor = Color(0xFFFFFFFF)
val UnSelectedItemColor = Color(0xFFEDE6DF)

private val DefaultColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = AccentColor,
    onTertiary = UnSelectedItemColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    onPrimary = TextColor,
    onSecondary = OnSecondaryColor,
    onBackground = TextColor,
    onSurface = TextColor,
)

@Composable
fun MovieAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DefaultColorScheme,
        typography = Typography,
        content = content
    )
}

