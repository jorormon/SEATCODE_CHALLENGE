package com.jordiortuno.rover.domain

sealed interface Error {
    data class GenericError(var message: String? = null) : Error {
        override fun toString(): String {
            return message ?: "Unknown error"
        }
    }
}