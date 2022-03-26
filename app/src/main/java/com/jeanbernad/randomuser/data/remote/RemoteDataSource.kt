package com.jeanbernad.randomuser.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
        private val userService: UserService
) : DataSource() {
    suspend fun getUser() = getResult { userService.getUser() }
}