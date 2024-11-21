package com.sparkfusion.data.source

import com.sparkfusion.data.entity.CourseInfoListDataEntity
import com.sparkfusion.data.entity.CoursesListDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CourseService {

    @GET("courses?language=en")
    suspend fun getCourses(
        @Query("page") page: Int = 1
    ): Response<CoursesListDataEntity>

    @GET("courses?language=en&is_popular=true")
    suspend fun getPopularCourses(
        @Query("page") page: Int = 1
    ): Response<CoursesListDataEntity>

    @GET("courses/{id}")
    suspend fun getCourseById(@Path("id") id: Int): Response<CourseInfoListDataEntity>
}






















