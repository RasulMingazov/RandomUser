package com.jeanbernad.randomuser.data.repository

import com.jeanbernad.randomuser.data.remote.RemoteDataSource
import com.jeanbernad.randomuser.utils.performGetOperation
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
) {

    fun getUser() = performGetOperation {
        remoteDataSource.getUser()
    }

}