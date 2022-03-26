package com.jeanbernad.randomuser.data.remote

import com.jeanbernad.randomuser.data.enteties.User
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("/api")
    suspend fun getUser(): Response<User>
}