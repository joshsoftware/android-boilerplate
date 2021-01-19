package com.amit.data.model.api.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("first_name")
    val firstName: String,
    @PrimaryKey @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)