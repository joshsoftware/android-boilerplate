package com.amit.app.data.model.local.pref

import android.content.Context
import com.amit.app.App

object AppPreferenceStorage {
    private const val mAppPref = "mAppPref"

    private const val IS_APP_OPEN_FIRST_TIME = "is_app_open_first_time"
    private const val LOGGED_IN = "logged_in"

    fun saveIsAppOpenFirstTime(status: Boolean) {
        val hxPrefs = App.mAppContext!!.getSharedPreferences(mAppPref, Context.MODE_PRIVATE)
        val editor = hxPrefs.edit()
        editor.putBoolean(IS_APP_OPEN_FIRST_TIME, status)

        isAppOpenFirstTime = status
        editor.apply()
    }

    fun saveUserLoggedIn(status: Boolean) {
        val hxPrefs = App.mAppContext!!.getSharedPreferences(mAppPref, Context.MODE_PRIVATE)
        val editor = hxPrefs.edit()
        editor.putBoolean(LOGGED_IN, status)

        userLoggedIn = status
        editor.apply()
    }

    fun removePreference() {
        val hxPrefs = App.mAppContext!!.getSharedPreferences(mAppPref, Context.MODE_PRIVATE)
        val editor = hxPrefs.edit()

        editor.clear()
        editor.apply()
    }

    var isAppOpenFirstTime: Boolean? = false
        get() {
            val hxPrefs = App.mAppContext!!.getSharedPreferences(mAppPref, Context.MODE_PRIVATE)
            isAppOpenFirstTime = hxPrefs.getBoolean(IS_APP_OPEN_FIRST_TIME, false)
            return field
        }
        private set

    var userLoggedIn: Boolean? = false
        get() {
            val hxPrefs = App.mAppContext!!.getSharedPreferences(mAppPref, Context.MODE_PRIVATE)
            userLoggedIn = hxPrefs.getBoolean(LOGGED_IN, false)
            return field
        }
        private set
}