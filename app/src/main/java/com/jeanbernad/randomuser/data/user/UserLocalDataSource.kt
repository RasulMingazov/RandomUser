package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.data.user.local.UserDao
import com.jeanbernad.randomuser.data.user.local.UserLocalModel

interface UserLocalDataSource {
    suspend fun user(): UserLocalModel

    suspend fun allUsers(): List<UserLocalModel>

    suspend fun insert(userLocalModel: UserLocalModel)

    class Base(private val userDao: UserDao) : UserLocalDataSource {
        override suspend fun user() = userDao.user()

        override suspend fun allUsers() = userDao.allUsers()

        override suspend fun insert(userLocalModel: UserLocalModel) =
            userDao.insert(userLocalModel)
    }
}