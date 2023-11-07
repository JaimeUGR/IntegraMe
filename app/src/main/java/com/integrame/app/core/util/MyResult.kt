package com.integrame.app.core.util

sealed class MyResult<out T, out E> {
    data class Success<T>(val value: T) : MyResult<T, Nothing>()
    data class Error<E>(val error: E) : MyResult<Nothing, E>()

    fun <R> map(transform: (T) -> R): MyResult<R, E> {
        return when (this) {
            is Success -> Success(transform(value))
            is Error -> this
        }
    }

    fun unwrap() : T {
        return when (this) {
            is Success -> this.value
            is Error -> {
                if (this.error is Exception)
                    throw this.error
                else
                    throw Exception()
            }
        }
    }
}
