package com.nayan.network.errorhandling

interface ErrorHandler {
    fun getErrorMessageFrom(ex: Exception): String?
}