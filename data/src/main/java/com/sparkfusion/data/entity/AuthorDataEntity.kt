package com.sparkfusion.data.entity

import com.google.gson.annotations.SerializedName

data class AuthorDataEntity(

    @SerializedName("id")
    val id: Int,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("avatar")
    val avatar: String
)
