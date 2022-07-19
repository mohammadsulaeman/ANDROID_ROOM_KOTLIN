package com.example.roomkotlin.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomkotlin.model.Users

@Dao
interface UsersDao {

    @Insert
    fun insert(users: Users)

    @Update
    fun update(users: Users)

    @Delete
    fun delete(users: Users)

    @Query("select * from users_table order by fullname desc")
    fun getAllUsers() : LiveData<List<Users>>

    @Query("delete from users_table")
    fun deleteAllUsers()
}