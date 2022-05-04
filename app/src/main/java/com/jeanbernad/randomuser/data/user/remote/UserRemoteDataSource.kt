package com.jeanbernad.randomuser.data.user.remote

interface UserRemoteDataSource {
    suspend fun user(): UserRemoteModel

    class Base(private val service: UserService) : UserRemoteDataSource {
        override suspend fun user() = service.user().results[0]
    }
}