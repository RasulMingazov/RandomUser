package com.jeanbernad.randomuser.data.user.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

interface UserDatabase {
    fun userDao(): UserDao

    abstract class AbstractRoom(context: Context, databaseName: String) : UserDatabase {

        @Database(entities = [UserLocalModel::class], version = 2, exportSchema = false)
        abstract class UsersRoom : RoomDatabase() {
            abstract fun userDao(): UserDao
        }

        private var builder: UsersRoom = androidx.room.Room.databaseBuilder(
            context,
            UsersRoom::class.java,
            databaseName
        ).build()

        override fun userDao() = builder.userDao()
    }

    class BaseRoom(context: Context) : AbstractRoom(context, "users")

    class TestRoom(context: Context) : AbstractRoom(context, "users_test")
}