package com.sparkfusion.core.common

import com.sparkfusion.core.exception.AnswerHasNoMapperException
import com.sparkfusion.core.exception.AnswerMappingException
import com.sparkfusion.core.exception.MainException

sealed class Answer<out T> {

    abstract fun unwrap(): T
    abstract suspend fun <R> suspendMap(mapper: (suspend (T) -> R)? = null): Answer<R>

    data class Success<out T>(private val value: T) : Answer<T>() {

        override fun unwrap(): T = value

        override suspend fun <R> suspendMap(mapper: (suspend (T) -> R)?): Answer<R> {
            return if (mapper == null) Failure(AnswerHasNoMapperException())
            else try {
                Success(mapper(unwrap()))
            } catch (e: Exception) {
                Failure(AnswerMappingException())
            }
        }
    }

    data class Failure(val exception: MainException) : Answer<Nothing>() {

        override fun unwrap(): Nothing = throw exception

        override suspend fun <R> suspendMap(mapper: (suspend (Nothing) -> R)?): Answer<R> {
            return this
        }
    }

    inline fun onSuccess(action: (T) -> Unit): Answer<T> {
        if (this is Success) action(unwrap())
        return this
    }

    inline fun onFailure(action: (MainException) -> Unit): Answer<T> {
        if (this is Failure) action(exception)
        return this
    }
}