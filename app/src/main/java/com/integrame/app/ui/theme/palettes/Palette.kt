package com.integrame.app.ui.theme.palettes

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

data class Palette(
    val paletteName: String,
    val displayName: String,
    val displayColor: Color,
    val lightColors: ColorScheme,
    val darkColors: ColorScheme
)
