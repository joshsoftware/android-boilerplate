package com.amit.data.database

import android.content.Context
import com.amit.data.model.api.response.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseArchitectureRoomRepository(application: Context) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    var baseArchitectureDao: BaseArchitectureDao?

    init {
        val db = BaseArchitectureDatabase.getDatabase(application)
        baseArchitectureDao = db?.sphTechDao()
    }

    fun saveList(userList: List<User>) {
        launch { saveListBG(userList) }
    }

    private suspend fun saveListBG(userList: List<User>) {
        withContext(Dispatchers.IO) {
            baseArchitectureDao?.saveList(userList)
        }
    }

    fun getList() = baseArchitectureDao?.getList()
}
