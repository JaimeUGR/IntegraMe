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
