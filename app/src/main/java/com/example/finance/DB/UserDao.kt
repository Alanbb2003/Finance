package com.example.finance.DB

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user:UserEntity)

    @Update
    suspend fun update(user:UserEntity)

    @Delete
    suspend fun delete(user:UserEntity)

    @Query("SELECT * FROM users")
    suspend fun fetch():List<UserEntity>

    @Query("SELECT * FROM users where username = :username")
    suspend fun get(username:String):UserEntity?

    @Query("SELECT * FROM users where username = :username and password =:password")
    suspend fun check(username: String,password:String):UserEntity?

    @Query("SELECT * FROM users WHERE username like :username AND username not LIKE :user")
    suspend fun search(username:String,user:String):List<UserEntity>?
}