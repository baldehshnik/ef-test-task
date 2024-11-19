package com.sparkfusion.ef_test_task.course.di

import com.sparkfusion.ef_test_task.course.repository.DetailsRepository
import com.sparkfusion.ef_test_task.course.router.AdapterDetailsRouter
import com.sparkfusion.features.details.domain.repository.IDetailsRepository
import com.sparkfusion.features.details.presentation.router.IDetailsRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DetailsModule {

    @Binds
    fun bindAdapterDetailsRouterToIDetailsRouter(adapterDetailsRouter: AdapterDetailsRouter): IDetailsRouter

    @Binds
    fun bindDetailsRepositoryToIDetailsRepository(detailsRepository: DetailsRepository): IDetailsRepository
}
