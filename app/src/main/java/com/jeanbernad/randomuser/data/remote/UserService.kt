package com.jeanbernad.randomuser.data.remote

import retrofit2.http.GET

interface UserService {

    @GET("/api")
    suspend fun user(): UserResponse
}