package com.sparkfusion.data.di

import com.sparkfusion.data.repository.DataRepository
import com.sparkfusion.data.repository.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindDataRepositoryToIDataRepository(dataRepository: DataRepository): IDataRepository
}