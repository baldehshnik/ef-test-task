package com.sparkfusion.data.utils

import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.exception.MainException
import com.sparkfusion.core.exception.NotFoundException
import retrofit2.Response

class ApiListResponseHandler<R>(
    private val response: Response<List<R>>,
    private val handleExceptionCode: (code: Int) -> MainException = ::handleExceptionCode
) {

    fun handleFetchedData(): Answer<List<R>> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body == null) Answer.Failure(NotFoundException())
            else Answer.Success(body)
        } else {
            Answer.Failure(handleExceptionCode(response.code()))
        }
    }
}