package com.example.roomkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class Users(
    @ColumnInfo(name = "fullname")
    val fullName : String,
    val alamat : String,
    val phone : String,
    val email : String,
    val dob : String,
    val gender : String,
    @PrimaryKey(autoGenerate = false) val id : Int? = null
)