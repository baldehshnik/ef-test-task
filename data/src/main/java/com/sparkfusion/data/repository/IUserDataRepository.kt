package com.sparkfusion.data.repository

import com.sparkfusion.core.common.Answer
import com.sparkfusion.data.entity.AuthorsListDataEntity

interface IUserDataRepository {

    suspend fun readUserById(id: Int): Answer<AuthorsListDataEntity>
}