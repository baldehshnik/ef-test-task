package com.sparkfusion.ef_test_task.home.factory

import com.sparkfusion.core.common.MapFactory
import com.sparkfusion.data.entity.CourseEntity
import com.sparkfusion.features.home.domain.model.CourseModel
import javax.inject.Inject

class HomeCourseFactory @Inject constructor() : MapFactory<CourseEntity, CourseModel> {

    override suspend fun mapTo(input: CourseEntity): CourseModel = with(input) {
        CourseModel(id, summary, cover, description, created, price)
    }

    override suspend fun mapFrom(input: CourseModel): CourseEntity = with(input) {
        CourseEntity(id, summary, cover, description, created, price)
    }

}
