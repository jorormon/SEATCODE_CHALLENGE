package com.jordiortuno.rover.domain

sealed class Result<out S : Any?, out F : Any> {
    data class Success<out S : Any?>(val data: S) : Result<S, Nothing>()
    data class Fail(val error: Error) : Result<Nothing, Error>()

    inline fun <T> fold(
        onFail: (Error) -> T,
        onSuccess: (S) -> T
    ): T {
        return when (this) {
            is Fail -> onFail(error)
            is Success -> onSuccess(data)
        }
    }

    fun orNull(): S? = fold(onFail = { null }, onSuccess = { it })
}