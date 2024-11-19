package com.sparkfusion.ef_test_task.course.factory

import com.sparkfusion.core.common.MapFactory
import com.sparkfusion.data.entity.AuthorDataEntity
import com.sparkfusion.features.details.domain.model.AuthorModel
import javax.inject.Inject

class AuthorDataEntityFactory @Inject constructor() : MapFactory<AuthorDataEntity, AuthorModel> {

    override suspend fun mapTo(input: AuthorDataEntity): AuthorModel = with(input) {
        AuthorModel(id, fullName, avatar)
    }

    override suspend fun mapFrom(input: AuthorModel): AuthorDataEntity = with(input) {
        AuthorDataEntity(id, fullName, avatar)
    }
}

















