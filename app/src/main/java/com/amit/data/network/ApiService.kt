package com.amit.data.network

import com.amit.data.model.api.request.LoginRequest
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ApiService @Inject constructor(
    builder: Retrofit.Builder
) {
    var baseUrl = "https://reqres.in/"

    // API client
    var apiClient =
        builder.baseUrl(baseUrl)
            .build()
            .create(ApiClient::class.java)

    fun callLoginAPI(request: LoginRequest) = apiClient.doLogin(request)

    fun callUserListingAPI() = apiClient.getUsers()

}
