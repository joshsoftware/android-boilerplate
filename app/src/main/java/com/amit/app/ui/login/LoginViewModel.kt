package com.amit.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amit.app.data.model.api.request.LoginRequest
import com.amit.app.data.model.api.response.LoginResponse
import com.amit.app.data.model.local.binding.LoginUser
import com.amit.app.data.network.ErrorUtils
import com.amit.app.ui.base.BaseViewModel
import com.amit.app.util.InputValidatorHelper
import retrofit2.Response

class LoginViewModel : BaseViewModel() {

    val loginResponse = MutableLiveData<LoginResponse>()

    fun doLogin(loginUser: LoginUser?) {
        val validMessage = isValidInputs(loginUser!!.email, loginUser.password)
        if (validMessage.isEmpty()) {

            setProgress(true)
            val request = LoginRequest(loginUser.email, loginUser.password)
            mDisposable = mNetworkClient.doLoginApiCall(request, this)!!

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

    //Handle login response
    fun handleLoginResponse(response: Response<LoginResponse>) {
        when {
            response.isSuccessful -> {
//                showMessage(response?.body()?.message)
                loginResponse.postValue(response.body())
            }

            response.code() == 401 -> {
                showMessage(ErrorUtils.getErrorMessage(response))
                setHandleAuthorizationFailed(true)
            }

            else -> {
                val errorResponse = ErrorUtils.getErrorMessage(response)
                showMessage(errorResponse)
            }
        }
        setProgress(false)
    }

    fun handleApiErrors(errorMessage: String) {
        showMessage(errorMessage)
        setProgress(false)
    }

}