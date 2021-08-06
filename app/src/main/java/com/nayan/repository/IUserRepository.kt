package com.nayan.repository

import com.nayan.model.User

interface IUserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUser(email: String): User
}