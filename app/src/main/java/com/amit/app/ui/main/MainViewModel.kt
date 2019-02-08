package com.amit.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amit.app.BR
import com.amit.app.data.model.api.response.User
import com.amit.app.data.model.api.response.UserListResponse
import com.amit.app.data.network.ErrorUtils
import com.amit.app.ui.base.BaseViewModel
import retrofit2.Response
import java.util.ArrayList

class MainViewModel : BaseViewModel() {
    val userlistResponse = MutableLiveData<UserListResponse>()
    var mDataList: MutableList<User> = ArrayList()

    init {
        getUserList()
    }

    private fun getUserList() {
        setProgress(true)
        mDisposable = mNetworkClient.getUsersApiCall(this)!!
    }

    fun getUserListResponse(): LiveData<UserListResponse> {
        return userlistResponse
    }

    fun handleUserListResponse(response: Response<UserListResponse>?) {
        when {
            response!!.isSuccessful -> {
//                showMessage(response?.body()?.message)
                mDataList.addAll(response.body()!!.data)
                notifyPropertyChanged(BR._all)
                userlistResponse.postValue(response!!.body())
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

    fun handleApiErrors(failureMessage: String) {
        showMessage(failureMessage)
        setProgress(false)
    }

}