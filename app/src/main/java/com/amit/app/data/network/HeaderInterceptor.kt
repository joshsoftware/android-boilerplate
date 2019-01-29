package com.amit.app.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.amit.app.util.ApplicationConstant
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor constructor(/*var mContext: Context*/) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

//        if (!isOnline(mContext)) {
//            throw NoConnectivityException()
//        }

//        var request: Request? = null
        val request = chain?.request()?.newBuilder()
            ?.addHeader("Content-Type", ApplicationConstant.CONTENT_TYPE)
            ?.addHeader("Accept", ApplicationConstant.ACCEPT_TYPE)
            ?.addHeader("Locale", ApplicationConstant.LOCALE)
            ?.build()
            ?: chain?.request()
                ?.newBuilder()
                ?.build()


        return chain?.proceed(request)!!
    }

    /**
     * To check internet connection is available
     */
    private fun isOnline(mContext: Context): Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

}