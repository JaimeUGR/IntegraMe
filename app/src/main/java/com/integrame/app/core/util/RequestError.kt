package com.integrame.app.core.util

// NOTE: Se podría utilizar un enum asignado un código de error al enum
// y luego haciendo la conversión
sealed class RequestError<T>(val errorCode: Int)
