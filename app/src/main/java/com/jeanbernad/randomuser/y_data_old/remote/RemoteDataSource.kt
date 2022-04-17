package com.jeanbernad.randomuser.y_data_old.remote


class RemoteDataSource constructor(
    private val userService: UserService
) : DataSource() {
    suspend fun getUser() = getResult { userService.getUser() }
}