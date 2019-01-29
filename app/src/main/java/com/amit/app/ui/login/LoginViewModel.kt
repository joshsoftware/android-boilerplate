package com.amit.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amit.app.data.model.local.binding.LoginUser
import com.amit.app.ui.base.BaseViewModel
import com.amit.app.util.InputValidatorHelper

class LoginViewModel : BaseViewModel() {
    val pageNavigation = MutableLiveData<Int>()

    fun doLogin(loginUser: LoginUser?) {
        val validMessage = isValidInputs(loginUser!!.email, loginUser.password)
        if (validMessage.isEmpty()) {
            pageNavigation.postValue(1)
        } else {
            setErrorMessage(validMessage)
        }
    }

    /**
     * To validate email and password
     */
    private fun isValidInputs(email: String, password: String): String {
        if (email.isEmpty()) {
            return "Please enter valid email"
        }
        if (!InputValidatorHelper.isValidEmail(email)) {
            return "Please enter valid email"
        }

        if (!InputValidatorHelper.isValidPassword(password)) {
            return "Please enter valid password"
        }
        return ""
    }

    /**
     * For callback to navigate up page
     */
    fun getPageRedirection(): LiveData<Int> {
        return pageNavigation
    }
}