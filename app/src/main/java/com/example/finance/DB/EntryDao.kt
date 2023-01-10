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

    @Query("SELECT * FROM Entry where user=:user")
    suspend fun getall(user:String):List<EntryEntity>

    @Query("SELECT * FROM Entry where tipe = :tipe and user= :user")
    suspend fun getbytipe(tipe:String,user: String):List<EntryEntity>?

    @Query("SELECT * FROM Entry where tipetransaksi = :tipe and user =:user")
    suspend fun getbytipetrans(tipe:String,user: String):List<EntryEntity>?

    @Query("SELECT * FROM Entry where tipetransaksi = :tipetrans and tipe= :tipe and user= :user")
    suspend fun getbytipeandtrans(tipetrans:String,tipe:String,user: String):List<EntryEntity>?

//    @Query("SELECT tipe,SUM(total) as total FROM Entry WHERE user= :user")
//    suspend fun getTotal(user: String):List<EntryEntity>?
}