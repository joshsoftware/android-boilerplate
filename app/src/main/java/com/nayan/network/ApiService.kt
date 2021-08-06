package com.nayan.network

import com.nayan.model.User
import com.nayan.model.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST
    fun login(@Body request: LoginRequest): User
}