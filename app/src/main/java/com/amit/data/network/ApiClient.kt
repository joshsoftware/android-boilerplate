package com.amit.data.network

import com.amit.data.model.api.request.LoginRequest
import com.amit.data.model.api.response.LoginResponse
import com.amit.data.model.api.response.UserListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {


    @POST("api/login")
    fun doLogin(@Body loginRequest: LoginRequest): Deferred<Response<LoginResponse>>

    @GET("api/users?delay=3")
    fun getUsers(): Deferred<Response<UserListResponse>>


}