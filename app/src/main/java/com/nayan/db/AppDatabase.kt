package com.nayan.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nayan.db.dao.UserDao
import com.nayan.model.User

const val DATABASE_NAME = "MY_APP_NAME_DATABASE"
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}