package com.jordiortuno.rover.domain.infra

abstract class UseCase<in IN, out OUT : Any?> {

    @Suppress("UNCHECKED_CAST")
    suspend operator fun invoke(params: IN? = null): Result<OUT, Error> =

        try {
            Result.Success(block(params ?: Unit as IN))
        } catch (ex: Exception) {
            Result.Fail(handleError(ex))
        }

    protected abstract val block: suspend (param: IN) -> OUT

    protected open fun handleError(e: Exception): Error {
        return Error.GenericError(e.message)
    }
}