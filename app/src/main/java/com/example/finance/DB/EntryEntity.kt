package com.example.finance.DB

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Entry")
class EntryEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    val currency:  String,
    val total:Int,
    val tipe: String,
    val tipetransaksi: String,
    val note:String,
    val user:String
) {
}