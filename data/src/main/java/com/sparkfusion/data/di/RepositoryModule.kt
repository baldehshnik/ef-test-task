package com.sparkfusion.data.di

import com.sparkfusion.data.repository.CourseDataRepository
import com.sparkfusion.data.repository.ICourseDataRepository
import com.sparkfusion.data.repository.IUserDataRepository
import com.sparkfusion.data.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCourseDataRepositoryToICourseDataRepository(dataRepository: CourseDataRepository): ICourseDataRepository

    @Binds
    fun bindUserDataRepositoryToIUserDataRepository(userDataRepository: UserDataRepository): IUserDataRepository
}