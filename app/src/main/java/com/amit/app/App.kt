package com.amit.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.amit.app.data.network.RetrofitApiClient

class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mAppContext: Context
    }

    override fun onCreate() {
        super.onCreate()
//        RetrofitApiClient.getRetrofitClient()
        mAppContext = applicationContext
    }
}