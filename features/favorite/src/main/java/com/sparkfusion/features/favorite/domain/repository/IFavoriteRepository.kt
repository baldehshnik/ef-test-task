package com.sparkfusion.features.favorite.domain.repository

import com.sparkfusion.features.favorite.domain.model.LocalCourseModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {

    suspend fun readSavedCourses(): Flow<List<LocalCourseModel>>
}