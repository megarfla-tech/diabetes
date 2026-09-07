package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = GreenLight,
    onPrimary = SurfaceWhite,
    primaryContainer = GreenDark,
    secondary = AccentGold,
    tertiary = AccentOrange,
    background = Color(0xFF111816),
    surface = Color(0xFF16211E),
    onBackground = Color(0xFFF1F5F3),
    onSurface = Color(0xFFF1F5F3),
  )

private val LightColorScheme =
  lightColorScheme(
    primary = GreenPrimary,
    onPrimary = SurfaceWhite,
    primaryContainer = GreenSoft,
    secondary = GreenLight,
    tertiary = AccentOrange,
    background = BackgroundLight,
    surface = SurfaceWhite,
    onBackground = TextDark,
    onSurface = TextDark,
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
