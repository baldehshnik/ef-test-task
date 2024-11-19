package com.sparkfusion.ef_test_task.course.factory

import com.sparkfusion.core.common.MapFactory
import com.sparkfusion.data.entity.CourseInfoDataEntity
import com.sparkfusion.features.details.domain.model.CourseInfoModel
import javax.inject.Inject

class CourseInfoDataEntityFactory @Inject constructor() : MapFactory<CourseInfoDataEntity, CourseInfoModel> {

    override suspend fun mapTo(input: CourseInfoDataEntity): CourseInfoModel = with(input) {
        CourseInfoModel(id, summary, cover, description, created, price, authors)
    }

    override suspend fun mapFrom(input: CourseInfoModel): CourseInfoDataEntity = with(input) {
        CourseInfoDataEntity(id, summary, cover, description, created, price, authors)
    }
}














