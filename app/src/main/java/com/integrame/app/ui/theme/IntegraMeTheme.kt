/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

package com.integrame.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.integrame.app.ui.viewmodel.ThemeViewModel

object AppTheme {
    val palettes = listOf(
        com.integrame.app.ui.theme.palettes.orange.palette,
        com.integrame.app.ui.theme.palettes.candy.palette,
        com.integrame.app.ui.theme.palettes.lollipop.palette,
        com.integrame.app.ui.theme.palettes.diamond.palette,
        com.integrame.app.ui.theme.palettes.emerald.palette
    )
}

@Composable
fun IntegraMeTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    themeViewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable() () -> Unit
) {
    val paletteId by themeViewModel.paletteIdFlow.collectAsStateWithLifecycle()

    // Determinar la paleta seleccionada (en caso de error se pone la por defecto)
    val palette =
        if (paletteId >= AppTheme.palettes.size) {
            com.integrame.app.ui.theme.palettes.orange.palette
        } else {
            AppTheme.palettes[paletteId]
        }

    val colors = if (!useDarkTheme) {
        palette.lightColors
    } else {
        palette.darkColors
    }

    // Importar la tipografía desde R

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}