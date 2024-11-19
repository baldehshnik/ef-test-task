package com.sparkfusion.ef_test_task.course.factory

import com.sparkfusion.core.common.MapFactory
import com.sparkfusion.data.entity.LocalCourseDataEntity
import com.sparkfusion.features.details.domain.model.LocalCourseModel
import javax.inject.Inject

class LocalCourseDataEntityFactory @Inject constructor() : MapFactory<LocalCourseDataEntity, LocalCourseModel> {

    override suspend fun mapTo(input: LocalCourseDataEntity): LocalCourseModel = with(input) {
        LocalCourseModel(id, summary, cover, description, created, price)
    }

    override suspend fun mapFrom(input: LocalCourseModel): LocalCourseDataEntity = with(input) {
        LocalCourseDataEntity(id, summary, cover, description, created, price)
    }
}














