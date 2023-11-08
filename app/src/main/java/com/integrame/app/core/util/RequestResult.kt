package com.integrame.app.core.util

sealed interface RequestResult<T> {
    data class Result<T>(val data: T) : RequestResult<T>
    // TODO: Añadir errores comunes?
    data class Error<T> (val error: String): RequestResult<T>
}

sealed interface AuthRequestResult<T> {
    data class Authorized<T>(val data: T) : AuthRequestResult<T>
    class Unauthorized<T> : AuthRequestResult<T>
    data class Error<T>(val error: String) : AuthRequestResult<T>
}
