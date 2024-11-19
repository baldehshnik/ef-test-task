package com.sparkfusion.data.source

import com.sparkfusion.data.entity.AuthorsListDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthorService {

    @GET("users/{authorId}")
    suspend fun readUserById(@Path("authorId") id: Int): Response<AuthorsListDataEntity>
}
