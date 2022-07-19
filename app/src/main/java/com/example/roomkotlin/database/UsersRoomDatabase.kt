package com.example.roomkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomkotlin.dao.UsersDao
import com.example.roomkotlin.model.Users
import com.example.roomkotlin.utils.subscribeOnBackground

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UsersRoomDatabase : RoomDatabase()
{
    abstract fun usersDao() : UsersDao

    companion object {
        private var instance : UsersRoomDatabase? = null

        @Synchronized
        fun getDatabaseUsers(context: Context): UsersRoomDatabase {
            if (instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                UsersRoomDatabase::class.java,
                "users_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build()
            }
            return instance!!
        }

        private val roomCallBack = object :Callback()
        {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populatedDatabase(instance!!)
            }
        }

        private fun populatedDatabase(usersRoomDatabase: UsersRoomDatabase)
        {
            val usersDao = usersRoomDatabase.usersDao()
            subscribeOnBackground {
                var users = Users(
                    "sulaeman",
                    "bekasi",
                    "08342113411",
                    "sulaeman@email.com",
                    "23 juli 1998",
                    "pria"
                )
                usersDao.insert(users)
            }
        }
    }
}