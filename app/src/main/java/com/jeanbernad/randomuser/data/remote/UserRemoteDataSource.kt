package com.jeanbernad.randomuser.data.remote

interface UserRemoteDataSource {
    suspend fun user(): List<UserRemoteModel>

    class Base(private val service: UserService) : UserRemoteDataSource {
        override suspend fun user() = service.user().results
    }
}