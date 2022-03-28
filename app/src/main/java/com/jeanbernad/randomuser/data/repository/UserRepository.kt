package com.jeanbernad.randomuser.data.repository

import com.jeanbernad.randomuser.data.local.UserDao
import com.jeanbernad.randomuser.data.remote.RemoteDataSource
import com.jeanbernad.randomuser.utils.performGetOperation
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: UserDao
) {

    fun user() = performGetOperation(
            databaseQuery = { localDataSource.user() },
            networkCall = { remoteDataSource.getUser() },
            saveCallResult = { localDataSource.insert(it) }
    )

}