package com.nayan.network.errorhandling

import android.content.Context
import com.google.gson.Gson
import com.nayan.model.response.AuthErrorResponse
import com.nayan.model.response.BadRequestErrorResponse
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

/**
 * Check for null in your activity so that context dependent code of displaying
 * default text message from string resource is in activity only and this one can be unit tested
 */
class HttpErrorHandler @Inject constructor(
    private val gson: Gson
): ErrorHandler {
    override fun getErrorMessageFrom(ex: Exception): String? {
        if(ex is HttpException) {
            ex.response()?.errorBody()?.let { responseBody ->
                return when(ex.code()) {
                    400 -> {
                        val badRequestError = gson.fromJson(responseBody.string(), BadRequestErrorResponse::class.java)
                        badRequestError.message
                    }
                    401 -> {
                        val authError = gson.fromJson(responseBody.string(), AuthErrorResponse::class.java)
                        authError.message
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