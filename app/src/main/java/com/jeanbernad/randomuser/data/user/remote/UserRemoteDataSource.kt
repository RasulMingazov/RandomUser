package com.jeanbernad.randomuser.data.user.remote

import javax.inject.Inject

interface UserRemoteDataSource {
    suspend fun user(): UserRemoteModel

    class Base @Inject constructor(private val service: UserService) : UserRemoteDataSource {
        override suspend fun user() = service.user().results[0]
    }
}