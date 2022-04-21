package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.data.user.remote.UserRemoteModel
import com.jeanbernad.randomuser.data.user.remote.UserService

interface UserRemoteDataSource {
    suspend fun user(): UserRemoteModel

    class Base(private val service: UserService) : UserRemoteDataSource {
        override suspend fun user() = service.user().results[0]
    }
}