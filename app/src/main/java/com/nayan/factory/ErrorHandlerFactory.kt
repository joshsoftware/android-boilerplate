package com.nayan.factory

import com.nayan.di.Http
import com.nayan.di.PreLogin
import com.nayan.model.RequestType
import com.nayan.network.errorhandling.ErrorHandler
import com.nayan.network.errorhandling.HttpErrorHandler
import com.nayan.network.errorhandling.PreLoginErrorHandler
import javax.inject.Inject

class ErrorHandlerFactory @Inject constructor(
    @Http private val httpErrorHandler: ErrorHandler,
    @PreLogin private val preLoginErrorHandler: ErrorHandler
): IFactory<RequestType, ErrorHandler> {
    override fun create(data: RequestType): ErrorHandler {
        return when(data)
        {
            RequestType.POST_LOGIN -> {
                httpErrorHandler
            }
            RequestType.PRE_LOGIN -> {
                preLoginErrorHandler
            }
        }
    }
}