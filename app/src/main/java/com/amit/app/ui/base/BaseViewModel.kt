package com.amit.app.ui.base

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable


abstract class BaseViewModel : ViewModel(), Observable {

    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

//    protected var mNetworkClient: NetworkClient = NetworkClient(RetrofitClientHelper.getNetworkServices())

    private val showProcess = MutableLiveData<Boolean>()

    protected lateinit var mDisposable: Disposable

    protected val errorMessageData = MutableLiveData<String>()

    private val mIsLoading = ObservableBoolean(false)

    fun getErrorMessage(): LiveData<String> {
        return errorMessageData
    }

    fun setErrorMessage(errorMessage: String) {
        errorMessageData.postValue(errorMessage)
    }

    override fun onCleared() {
        if (::mDisposable.isInitialized)
            mDisposable.dispose()
        super.onCleared()
    }

//    fun getLoggedInUser(): UserData? {
//        if (PreferencesHelper.getUser() != null) {
//            val jsonLoggedInUser = PreferencesHelper.getUser()
//            return jsonLoggedInUser as UserData
//        } else {
//            return null
//        }
//    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.remove(callback)
    }


    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, fieldId, null)
    }

    fun getProgress(): LiveData<Boolean> {
        return showProcess
    }


    protected fun setProgress(boolean: Boolean) {
        showProcess.value = (boolean)
    }

}