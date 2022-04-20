package com.jeanbernad.randomuser.data

import com.jeanbernad.randomuser.data.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.data.remote.UsersRemoteMapper

interface UserRepository<T> {
    suspend fun user(): T
}

class BaseUserRepository<T>(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val usersRemoteMapper: UsersRemoteMapper,
    private val mapper: UsersDataToDomainMapper<T>
) : UserRepository<T> {

    override suspend fun user(): T {
        val dataObject = try {
            val upcomingRemoteList = userRemoteDataSource.user()
            val upcomingList = usersRemoteMapper.map(upcomingRemoteList)
            UsersData.Success(upcomingList)
        } catch (e: Exception) {
            UsersData.Fail(e)
        }
        return dataObject.map(mapper)
    }
}