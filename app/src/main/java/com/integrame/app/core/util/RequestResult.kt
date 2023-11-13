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
