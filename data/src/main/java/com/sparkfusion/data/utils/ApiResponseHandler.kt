package com.sparkfusion.data.utils

import android.util.Log
import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.exception.MainException
import com.sparkfusion.core.exception.NotFoundException
import retrofit2.Response

class ApiResponseHandler<R>(
    private val response: Response<R>,
    private val handleExceptionCode: (code: Int) -> MainException
) {

    fun handleFetchedData(): Answer<R> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body == null) Answer.Failure(NotFoundException())
            else Answer.Success(body)
        } else {
            Log.i("TAGTAG", "" + response.code() + " - " + response.errorBody()?.string().toString())
            Answer.Failure(handleExceptionCode(response.code()))
        }
    }
}
