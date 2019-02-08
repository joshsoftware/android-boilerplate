package com.amit.app.data.network

import com.amit.app.data.model.api.request.LoginRequest
import com.amit.app.data.model.api.response.LoginResponse
import com.amit.app.data.model.api.response.UserListResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkServices {
    @POST("api/login")
    fun doLogin(@Body loginRequest: LoginRequest): Observable<Response<LoginResponse>>

    @GET("api/users?delay=3")
    fun getUsers(): Observable<Response<UserListResponse>>
}