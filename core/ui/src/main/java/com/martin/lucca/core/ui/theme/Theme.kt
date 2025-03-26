package com.martin.lucca.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorPalette = lightColorScheme(
    primary = Blue40,
    onPrimary = Color.White,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    inversePrimary = Blue80,
    background = WhiteSolid,
    onBackground = Grey10,
    surface = WhiteSolid,
    onSurface = Grey30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey90,
    surfaceVariant = Color.White,
    onSurfaceVariant = Grey30,
)

@Composable
fun LuccaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> LightColorPalette
    }

    MaterialTheme.colorScheme.onError

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}