package com.nayan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nayan.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepo: IUserRepository
): BaseViewModel() {
    private val _sampleApiCallResult = MutableLiveData<String>()
    val sampleApiCallResult: LiveData<String>
    get() = _sampleApiCallResult

    /**
     * No need to handle errors seperately as executeRoutine handles errors and navigates them to BaseActivity check Baseactivity implementation and execute routine implementation
     */
    fun sampleApiCall() {
        executeRoutine {
            _sampleApiCallResult.postValue("testresponse" /*userRepo.getUser*/)
        }
    }
}