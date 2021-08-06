package com.nayan

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


private const val MY_APP_NAME_SHARED_PREFERENCES = "MY_APP_NAME_SHARED_PREFERENCES"
private const val KEY_TOKEN = "KEY_TOKEN"

class AppSharedPreferences  @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        MY_APP_NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    fun saveToken(token: String?) {
        sharedPreferences.putValues {
            val tokenData = Gson().toJson(token)
            it.putString(KEY_TOKEN, tokenData)
        }
    }
    var token: String? = null
        get() {
            token = sharedPreferences.getString(KEY_TOKEN, token)
            return field
        }

    /**
     * Extension function for shared preferences
     */
    private fun SharedPreferences.putValues(func:(SharedPreferences.Editor)-> Unit) {
        val editor: SharedPreferences.Editor = this.edit()
        func(editor)
        editor.apply()
    }
}