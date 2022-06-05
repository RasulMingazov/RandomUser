package com.jeanbernad.randomuser.data.user.local

interface UserLocalDataSource {
    suspend fun user(): UserLocalModel

    suspend fun users(): List<UserLocalModel>

    suspend fun insert(userLocalModel: UserLocalModel)

    suspend fun countUsers() : Int

    class Base(private val userDao: UserDao) : UserLocalDataSource {
        override suspend fun user() = userDao.user()

        override suspend fun users() = userDao.allUsers()

        override suspend fun insert(userLocalModel: UserLocalModel) =
            userDao.insert(userLocalModel)

        override suspend fun countUsers() = userDao.countUsers()
    }
}