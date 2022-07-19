package com.example.roomkotlin.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomkotlin.dao.UsersDao
import com.example.roomkotlin.database.UsersRoomDatabase
import com.example.roomkotlin.model.Users
import com.example.roomkotlin.utils.subscribeOnBackground

class UsersRepository(application: Application)
{
    private var usersDao : UsersDao
    private var allUsers : LiveData<List<Users>>

    private val database = UsersRoomDatabase.getDatabaseUsers(application)

    init {
        usersDao = database.usersDao()
        allUsers = usersDao.getAllUsers()
    }

    // insert data
    fun insert(users: Users)
    {
        subscribeOnBackground {
            usersDao.insert(users)
        }
    }

    // update data
    fun update(users: Users){
        subscribeOnBackground {
            usersDao.update(users)
        }
    }

    // delete data
    fun delete(users: Users){
        subscribeOnBackground {
            usersDao.delete(users)
        }
    }

    // delete semua data
    fun deleteAllUsers(){
        subscribeOnBackground {
            usersDao.deleteAllUsers()
        }
    }

    // menampilkan data
    fun getAllUsers() : LiveData<List<Users>>{
        return allUsers
    }
}