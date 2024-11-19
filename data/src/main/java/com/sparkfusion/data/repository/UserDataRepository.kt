package com.sparkfusion.data.repository

import com.sparkfusion.core.common.Answer
import com.sparkfusion.core.dispatchers.IODispatcher
import com.sparkfusion.data.entity.AuthorsListDataEntity
import com.sparkfusion.data.source.AuthorService
import com.sparkfusion.data.utils.ApiResponseHandler
import com.sparkfusion.data.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val authorService: AuthorService
) : IUserDataRepository {

    override suspend fun readUserById(id: Int): Answer<AuthorsListDataEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(authorService.readUserById(id)).handleFetchedData()
        }
}


















