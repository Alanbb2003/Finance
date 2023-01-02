package com.example.finance.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    UserEntity::class,
    EntryEntity::class
], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val userDao: UserDao
    abstract val entryDao: EntryDao
    //Line di bawah ini mostly hafalan
    companion object {
        private var _database: AppDatabase? = null

        fun build(context: Context?): AppDatabase {
            if (_database == null) {
                //
                _database =
                    Room.databaseBuilder(context!!, AppDatabase::class.java, "FinanceDb")
                        .build()
            }
            return _database!!
        }
    }
}