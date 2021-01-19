package com.amit.ui.base

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amit.core.exception.Failure
import com.amit.core.result.Event

/**
 * Base ViewModel class with default Failure handling.
 * @see ViewModel
 * @see Failure
 */
abstract class BaseViewModel : ViewModel(), Observable {

    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null
    var failure: MutableLiveData<Event<String>> = MutableLiveData()
    var sessionExpire: MutableLiveData<String> = MutableLiveData()

    private val showProcess = MutableLiveData<Boolean>()
    protected val messageData = MutableLiveData<String>()
    protected val authorizationFailedData = MutableLiveData<Boolean>()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = Event(failure.javaClass.toString())
    }


    fun getProgress(): LiveData<Boolean> {
        return showProcess
    }

    fun setProgress(boolean: Boolean) {
        showProcess.postValue(boolean)
    }

    fun getMessage(): LiveData<String> {
        return messageData
    }

    fun showMessage(message: String) {
        messageData.postValue(message)
    }

    fun getAuthorizationFailedListener(): LiveData<Boolean> {
        return authorizationFailedData;
    }

    protected fun setHandleAuthorizationFailed(boolean: Boolean) {
        if (boolean) {
            authorizationFailedData.value = (boolean)
        }
    }

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

}