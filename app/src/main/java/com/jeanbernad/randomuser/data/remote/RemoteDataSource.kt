package com.jeanbernad.randomuser.data.remote


class RemoteDataSource constructor(
    private val userService: UserService
) : DataSource() {
    suspend fun getUser() = getResult { userService.getUser() }
}