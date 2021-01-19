package com.amit.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amit.data.model.api.response.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)

abstract class BaseArchitectureDatabase : RoomDatabase() {

    abstract fun sphTechDao(): BaseArchitectureDao

    companion object {

        @Volatile
        private var INSTANCE: BaseArchitectureDatabase? = null
        const val DATABASE_NAME = "base_architecture_database"

        fun getDatabase(context: Context): BaseArchitectureDatabase? {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    BaseArchitectureDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }
}
