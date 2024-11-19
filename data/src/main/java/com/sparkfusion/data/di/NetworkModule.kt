package com.sparkfusion.data.di

import com.sparkfusion.data.source.AuthorService
import com.sparkfusion.data.source.CourseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://stepik.org/api/")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideCourseService(retrofit: Retrofit): CourseService {
        return retrofit.create(CourseService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthorService(retrofit: Retrofit): AuthorService {
        return retrofit.create(AuthorService::class.java)
    }
}














