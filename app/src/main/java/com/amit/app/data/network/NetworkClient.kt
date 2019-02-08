package com.amit.app.data.network

import com.amit.app.data.model.api.request.LoginRequest
import com.amit.app.ui.login.LoginViewModel
import com.amit.app.ui.main.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException

class NetworkClient constructor(networkServices: NetworkServices?) {
    var mNetworkServices: NetworkServices? = null
    val internetError: String = "Please check your internet connection."

    init {
        this.mNetworkServices = networkServices
    }

    private fun getFailureMessage(t: Throwable?): String {
        if (t is IOException) {
            return internetError
        } else {
            return t?.message.toString()
        }
    }

    /**
     * To call api for server LOGIN
     */
    fun doLoginApiCall(
        loginRequest: LoginRequest,
        mViewModel: LoginViewModel
    ): Disposable? {
        return mNetworkServices?.doLogin(loginRequest)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(
                { t ->
                    mViewModel.handleLoginResponse(t)

                }, { t ->
                    mViewModel.handleApiErrors(getFailureMessage(t))
                })
    }

    /**
     * to call api for getUserList
     */

    fun getUsersApiCall(mViewModel: MainViewModel): Disposable? {
        return mNetworkServices?.getUsers()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(
                { t ->
                    mViewModel.handleUserListResponse(t)

                }, { t ->
                    mViewModel.handleApiErrors(getFailureMessage(t))
                })
    }
}