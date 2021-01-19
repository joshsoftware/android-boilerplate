package com.amit.data.network.repository

import com.amit.core.result.Results
import com.amit.data.model.api.request.LoginRequest
import com.amit.data.model.api.response.LoginResponse
import com.amit.data.model.api.response.UserListResponse
import com.amit.data.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: ApiService,
    private val baseAPIRepository: BaseAPIRepository
) {

    val genric_error = "Error occurred"

    //Login API
    suspend fun callLoginApi(loginRequest: LoginRequest): Results<LoginResponse> =
        baseAPIRepository.safeApiCall(
            call = {
                service.callLoginAPI(
                    loginRequest
                ).await()
            },
            errorMessage = genric_error
        )

    //User Listing API
    suspend fun callUserListingApi(): Results<UserListResponse> =
        baseAPIRepository.safeApiCall(
            call = {
                service.callUserListingAPI(
                ).await()
            },
            errorMessage = genric_error
        )

}