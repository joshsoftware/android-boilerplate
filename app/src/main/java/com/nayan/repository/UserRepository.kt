package com.nayan.repository

import com.nayan.db.AppDatabase
import com.nayan.db.dao.UserDao
import com.nayan.model.User
import com.nayan.network.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val dao: UserDao,
    private val apiService: ApiService
    ): IUserRepository {
    override suspend fun saveUser(user: User) {
        dao.saveUser(user)
    }

    override suspend fun getUser(email: String): User {
        return dao.getUserWith(email)
    }
}