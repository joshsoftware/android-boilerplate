package com.amit.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amit.app.BR
import com.amit.core.result.Event
import com.amit.core.result.Results
import com.amit.data.database.BaseArchitectureRoomRepository
import com.amit.data.model.api.response.User
import com.amit.data.model.api.response.UserListResponse
import com.amit.data.network.repository.Repository
import com.amit.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    context: Context,
    private val repository: Repository
) : BaseViewModel() {
    val userlistResponse = MutableLiveData<UserListResponse>()
    var mDataList: MutableList<User> = ArrayList()
    private var mRoomRepository: BaseArchitectureRoomRepository =
        BaseArchitectureRoomRepository(context)

    init {
        getUserList()
        getDBList()
    }

    private fun getUserList() {
        setProgress(true)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.callUserListingApi()) {
                is Results.Success -> {
                    saveToDB(result?.data?.data)
                    mDataList.addAll(result?.data.data)
                    notifyPropertyChanged(BR._all)
                    userlistResponse.postValue(result.data)
                }

                is Results.Error -> {
                    failure.postValue(Event(result.exception.message.toString()))
                }
            }
            setProgress(false)
        }
    }

    fun getUserListResponse(): LiveData<UserListResponse> {
        return userlistResponse
    }
    private fun saveToDB(userList: List<User>) {
        CoroutineScope(Dispatchers.IO).launch {
            mRoomRepository.saveList(userList)
        }
    }

    private fun getDBList() = viewModelScope.async {
        viewModelScope.launch(Dispatchers.IO) {
            mRoomRepository.getList()?.let {
                println("db success")
                for (i in it) {
                    Log.e("List ", i.firstName + " " + i.lastName)
                }
            }
        }
    }

}