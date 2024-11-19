package com.sparkfusion.ef_test_task.saved.di

import com.sparkfusion.ef_test_task.saved.repository.FavoriteRepository
import com.sparkfusion.ef_test_task.saved.router.AdapterFavoriteRouter
import com.sparkfusion.features.favorite.domain.repository.IFavoriteRepository
import com.sparkfusion.features.favorite.presentation.router.IFavoriteRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoriteModule {

    @Binds
    fun bindAdapterFavoriteRouterToIFavoriteRouter(favoriteRouter: AdapterFavoriteRouter): IFavoriteRouter

    @Binds
    fun bindFavoriteRepositoryToIFavoriteRepository(favoriteRepository: FavoriteRepository): IFavoriteRepository
}

