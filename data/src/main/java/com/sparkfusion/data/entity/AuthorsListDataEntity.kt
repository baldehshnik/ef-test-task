package com.sparkfusion.data.entity

import com.google.gson.annotations.SerializedName

data class AuthorsListDataEntity(

    @SerializedName("users")
    val authors: List<AuthorDataEntity>
)
