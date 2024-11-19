package com.sparkfusion.ef_test_task.home.di

import com.sparkfusion.ef_test_task.home.repository.HomeRepository
import com.sparkfusion.ef_test_task.home.router.AdapterHomeRouter
import com.sparkfusion.features.home.domain.repository.IHomeRepository
import com.sparkfusion.features.home.presentation.router.IHomeRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HomeModule {

    @Binds
    fun bindHomeRepositoryToIHomeRepository(homeRepository: HomeRepository): IHomeRepository

    @Binds
    fun bindAdapterHomeRouterToIHomeRouter(adapterHomeRouter: AdapterHomeRouter): IHomeRouter
}