package com.integrame.app.core.util

sealed class Option<out T> {
    data class Some<out T>(val value: T) : Option<T>()
    object None : Option<Nothing>()

    companion object {
        fun <T> of(value: T) : Some<T> {
            return Some(value)
        }
    }
}
