package com.jeanbernad.randomuser.y_data_old.repository

import com.jeanbernad.randomuser.y_data_old.remote.RemoteDataSource
import com.jeanbernad.randomuser.y_utils.performGetOperation

class UserRepository constructor(
    private val remoteDataSource: RemoteDataSource,
) {

    fun getUser() = performGetOperation {
        remoteDataSource.getUser()
    }

}