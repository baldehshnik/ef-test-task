package com.sparkfusion.data.utils

import android.util.Log
import androidx.paging.PagingSource
import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.exception.MainException
import com.sparkfusion.core.exception.NetworkException
import com.sparkfusion.core.exception.UnexpectedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> Answer<T>
): Answer<T> = withContext(dispatcher) {
    try {
        call.invoke()
    } catch (exception: Exception) {
        Log.i("TAGTAG", "exception - " + exception.message.toString())
        handleApiException(exception)
    }
}

suspend fun <K : Any, T : Any> safePagingApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> PagingSource.LoadResult<K, T>
): PagingSource.LoadResult<K, T> = withContext(dispatcher) {
    try {
        call.invoke()
    } catch (exception: Exception) {
        PagingSource.LoadResult.Error(handleApiException(exception).exception)
    }
}

fun handleExceptionCode(code: Int): MainException {
    return when (code) {
        else -> UnexpectedException()
    }
}

private fun handleApiException(exception: Exception): Answer.Failure {
    return Answer.Failure(
        when (exception) {
            is IOException -> NetworkException(exception)
            else -> UnexpectedException(exception)
        }
    )
}
