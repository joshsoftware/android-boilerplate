package com.nayan.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nayan.model.User

@Dao
abstract class UserDao {
    @Insert
    abstract suspend fun saveUser(user: User)

    @Query("SELECT * FROM User WHERE user.email = :email")
    abstract suspend fun getUserWith(email: String): User
}