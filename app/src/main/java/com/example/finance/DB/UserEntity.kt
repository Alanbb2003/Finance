package com.example.finance.DB

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
@Parcelize
data class UserEntity(
    @PrimaryKey
    val username: String,
    val name:  String,
    val email:String,
    val password: String
): Parcelable {

}