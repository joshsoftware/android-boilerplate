package com.nayan.network.errorhandling

import com.google.gson.Gson
import com.nayan.model.response.AuthErrorResponse
import com.nayan.model.response.BadRequestErrorResponse
import com.nayan.model.response.SampleOtherErrorResponse
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class PreLoginErrorHandler @Inject constructor(
    private val gson: Gson
): ErrorHandler {
    override fun getErrorMessageFrom(ex: Exception): String? {
        if(ex is HttpException) {
            ex.response()?.errorBody()?.let { responseBody ->
                return when(ex.code()) {
                    400 -> {
                        val badRequestError = gson.fromJson(responseBody.string(), SampleOtherErrorResponse::class.java)
                        badRequestError.otherMessage
                    } else -> {
                        null
                    }
                }
            }
        } else {
            return null
        }

        return null
    }
}