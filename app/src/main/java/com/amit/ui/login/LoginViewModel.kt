package com.amit.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amit.core.result.Event
import com.amit.core.result.Results
import com.amit.data.model.api.request.LoginRequest
import com.amit.data.model.api.response.LoginResponse
import com.amit.data.model.local.binding.LoginUser
import com.amit.data.network.repository.Repository
import com.amit.ui.base.BaseViewModel
import com.amit.util.InputValidatorHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    context: Context,
    private val repository: Repository
) : BaseViewModel() {

    val loginResponse = MutableLiveData<LoginResponse>()

    fun doLogin(loginUser: LoginUser?) {
        val validMessage = isValidInputs(loginUser!!.email, loginUser.password)
        if (validMessage.isEmpty()) {
            setProgress(true)
            val request = LoginRequest(loginUser.email, loginUser.password)
            viewModelScope.launch(Dispatchers.IO) {
                when (val result = repository.callLoginApi(request)) {
                    is Results.Success -> {
                        loginResponse.postValue(result.data)
                    }

                    is Results.Error -> {
                        failure.postValue(Event(result.exception.message.toString()))
                    }
                }
                setProgress(false)
            }
        } else {
            showMessage(validMessage)
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
//        if (!InputValidatorHelper.isValidPassword(password)) {
//            return "Please enter valid password"
//        }
        return ""
    }

    fun getLoginResponse(): LiveData<LoginResponse> {
        return loginResponse
    }


}