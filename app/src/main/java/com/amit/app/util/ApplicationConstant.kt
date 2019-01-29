package com.amit.app.util

import com.amit.app.data.network.Environment

object ApplicationConstant {

    //Flag for debugging log mode
    var isDebuggable = true

    //Api url component
    val SERVER_URL = Environment.PRODUCTION.baseUrl

    //Connection timeout
    val TIMEOUT_CONNECTION = 5
    val TIMEOUT_READ = 5

    //Http request header content
    val ACCEPT_TYPE = "application/json"
    val CONTENT_TYPE = "application/json"
    val LOCALE = "mr-IN"

    //Fragment transactions
    const val ADD_FRAGMENT = 1
    const val REPLACE_FRAGMENT = 2
    const val REMOVE_FRAGMENT = 3
}