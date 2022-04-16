package com.jeanbernad.randomuser.data.repository

import com.jeanbernad.randomuser.data.remote.RemoteDataSource
import com.jeanbernad.randomuser.utils.performGetOperation

class UserRepository constructor(
    private val remoteDataSource: RemoteDataSource,
) {

    fun getUser() = performGetOperation {
        remoteDataSource.getUser()
    }

}