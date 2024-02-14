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

package com.integrame.app.core.util

sealed interface RequestResult<out T> {
    data class Success<out T>(val data: T) : RequestResult<T>
    // TODO: Añadir errores comunes?
    data class Error<out T> (val error: String): RequestResult<T>
}

sealed interface AuthRequestResult<out T> {
    data class Authorized<out T>(val data: T) : AuthRequestResult<T>
    class Unauthorized<out T> : AuthRequestResult<T>

    // TODO: Hacer herencia para definir errores comunes
    data class Error<out T>(val error: String) : AuthRequestResult<T>
}
