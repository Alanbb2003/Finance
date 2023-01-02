package com.example.finance.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface EntryDao {
    @Insert
    suspend fun insert(Entry:EntryEntity)

    @Update
    suspend fun update(Entry:EntryEntity)

    @Delete
    suspend fun delete(Entry:EntryEntity)
}