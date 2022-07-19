package com.example.roomkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomkotlin.model.Users
import com.example.roomkotlin.repository.UsersRepository

class UsersViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository = UsersRepository(application)
    private val allUsers = repository.getAllUsers()

    fun insert(users: Users)
    {
        repository.insert(users)
    }

    fun update(users: Users)
    {
        repository.update(users)
    }

    fun delete(users: Users)
    {
        repository.delete(users)
    }

    fun deleteAllUsers(){
        repository.deleteAllUsers()
    }

    fun getAllUsers() : LiveData<List<Users>>{
        return allUsers
    }
}