package com.jeanbernad.randomuser.data.user.remote

import com.jeanbernad.randomuser.data.user.ToUserMapper
import com.jeanbernad.randomuser.data.user.UserData
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import com.jeanbernad.randomuser.data.user.UserRemoteDataSource

interface UserRepository<T> {
    suspend fun user(): T
}

class BaseUserRepository<T>(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val toUserMapper: ToUserMapper,
    private val mapper: UserDataToDomainMapper<T>
) : UserRepository<T> {

    override suspend fun user(): T {
        return (try {
            val upcomingRemoteUser = userRemoteDataSource.user()
            val upcomingUser = upcomingRemoteUser.map(toUserMapper)
            upcomingUser
        } catch (exception: Exception) {
            UserData.Fail(exception)
        }).map(mapper)
    }
}