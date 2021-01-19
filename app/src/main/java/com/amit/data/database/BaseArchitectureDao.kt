package com.amit.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amit.data.model.api.response.User

@Dao
interface BaseArchitectureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveList(userList: List<User>): Array<Long>

    @Query("SELECT * from user")
    fun getList(): List<User>
}