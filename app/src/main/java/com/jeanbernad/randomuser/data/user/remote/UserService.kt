package com.jeanbernad.randomuser.data.user.remote

import retrofit2.http.GET

interface UserService {

    @GET("/api")
    suspend fun user(): UserResponse
}