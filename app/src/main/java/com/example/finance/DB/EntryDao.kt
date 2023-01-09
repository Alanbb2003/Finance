package com.example.finance.DB

import androidx.room.*

@Dao
interface EntryDao {
    @Insert
    suspend fun insert(Entry:EntryEntity)

    @Update
    suspend fun update(Entry:EntryEntity)

    @Delete
    suspend fun delete(Entry:EntryEntity)

    @Query("SELECT * FROM Entry")
    suspend fun getall():List<EntryEntity>

    @Query("SELECT * FROM Entry where tipe = :tipe")
    suspend fun getbytipe(tipe:String):List<EntryEntity>?

    @Query("SELECT * FROM Entry where tipetransaksi = :tipe")
    suspend fun getbytipetrans(tipe:String):List<EntryEntity>?

    @Query("SELECT * FROM Entry where tipetransaksi = :tipetrans and tipe= :tipe")
    suspend fun getbytipeandtrans(tipetrans:String,tipe:String):List<EntryEntity>?
}