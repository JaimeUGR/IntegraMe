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

package com.integrame.app.core.data.repository

import android.content.Context
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "theme")

/**
 * Repositorio encargado de los datos referentes a los temas.
 *
 * &nbsp;
 *
 * ### Paleta de color
 * Maneja la paleta de colores seleccionada. Dicha paleta se almacena localmente en el
 * dispositivo (y no por cuenta de usuario en el servidor).
 */
class ThemeRepository @Inject constructor(
    private val context: Context
) {
    private val paletteKey = intPreferencesKey("palette")

    val paletteIdFlow = context.themeDataStore.data.map { prefs ->
        prefs[paletteKey] ?: 0
    }

    suspend fun setPaletteId(paletteId: Int) {
        context.themeDataStore.edit { theme ->
            theme[paletteKey] = paletteId
        }
    }
}