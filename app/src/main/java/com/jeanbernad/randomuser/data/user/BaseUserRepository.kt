package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.data.user.all.UsersData
import com.jeanbernad.randomuser.data.user.all.UsersDataToDomainMapper
import com.jeanbernad.randomuser.data.user.all.local.UsersLocalMapper
import com.jeanbernad.randomuser.data.user.local.ToUserLocalMapper
import com.jeanbernad.randomuser.data.user.local.UserLocalDataSource
import com.jeanbernad.randomuser.data.user.local.UserLocalModel
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.domain.user.UserRepository

class BaseUserRepository<T, M>(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val toUserDataMapper: ToUserDataMapper,
    private val toUserLocalMapper: ToUserLocalMapper<UserLocalModel>,
    private val userDataToDomainMapper: UserDataToDomainMapper<T>,
    private val usersLocalMapper: UsersLocalMapper,
    private val usersDataToDomainMapper: UsersDataToDomainMapper<M>
) : UserRepository<T, M> {

    override suspend fun user() = (try {
        val upcomingRemoteUser = userRemoteDataSource.user()
        val upcomingUser = upcomingRemoteUser.map(toUserDataMapper)
        userLocalDataSource.insert(upcomingUser.mapToLocal(toUserLocalMapper))
        upcomingUser
    } catch (exception: Exception) {
        if (userLocalDataSource.countUsers() == 0) {
            UserData.Fail(exception)
        } else {
            val upcomingLocalUser = userLocalDataSource.user()
            val upcomingUser = upcomingLocalUser.map(toUserDataMapper)
            upcomingUser
        }
    }).map(userDataToDomainMapper)

    override suspend fun users(): M = (try {
        if (userLocalDataSource.countUsers() != 0) {
            val pokemonEntityList = userLocalDataSource.users()
            UsersData.Success(usersLocalMapper.map(pokemonEntityList))
        } else {
            UsersData.Empty
        }
    } catch (exception: Exception) {
        UsersData.Fail(exception)
    }).map(usersDataToDomainMapper)
}