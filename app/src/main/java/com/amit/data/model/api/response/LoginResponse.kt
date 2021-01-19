package com.amit.data.model.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("token")
    val token: String
)