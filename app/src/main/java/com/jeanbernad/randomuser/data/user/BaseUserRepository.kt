package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.data.user.local.ToUserLocalMapper
import com.jeanbernad.randomuser.data.user.local.UserLocalDataSource
import com.jeanbernad.randomuser.data.user.local.UserLocalModel
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.domain.user.UserRepository

class BaseUserRepository<T>(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val toUserMapper: ToUserMapper,
    private val toUserLocalMapper: ToUserLocalMapper<UserLocalModel>,
    private val mapper: UserDataToDomainMapper<T>
) : UserRepository<T> {

    override suspend fun user(): T {
        return (try {
            val upcomingRemoteUser = userRemoteDataSource.user()
            val upcomingUser = upcomingRemoteUser.map(toUserMapper)
            userLocalDataSource.insert(upcomingUser.mapToLocal(toUserLocalMapper))
            upcomingUser
        } catch (exception: Exception) {
            if (userLocalDataSource.countUsers() == 0) {
                UserData.Fail(exception)
            } else {
                val upcomingLocalUser = userLocalDataSource.user()
                val upcomingUser = upcomingLocalUser.map(toUserMapper)
                upcomingUser
            }
        }).map(mapper)
    }
}