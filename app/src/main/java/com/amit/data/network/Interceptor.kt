package com.amit.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class Interceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val mainRequest = chain.request()
        val builder = mainRequest.newBuilder()
            .method(mainRequest.method, mainRequest.body)

        return chain.proceed(builder.build())
    }
}