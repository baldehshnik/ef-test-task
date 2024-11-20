package com.sparkfusion.ef_test_task.home.factory

import com.sparkfusion.core.common.MapFactory
import com.sparkfusion.data.entity.LocalCourseDataEntity
import com.sparkfusion.features.home.domain.model.CourseModel
import javax.inject.Inject

class LocalCourseDataEntityFactory @Inject constructor() : MapFactory<LocalCourseDataEntity, CourseModel> {

    override suspend fun mapTo(input: LocalCourseDataEntity): CourseModel = with(input) {
        CourseModel(id, summary, cover, description, created, price)
    }

    override suspend fun mapFrom(input: CourseModel): LocalCourseDataEntity = with(input) {
        LocalCourseDataEntity(id, summary, cover, description, created, price)
    }
}
















