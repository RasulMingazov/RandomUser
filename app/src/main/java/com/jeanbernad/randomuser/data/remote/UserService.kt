package com.jeanbernad.randomuser.data.remote

import com.jeanbernad.randomuser.data.remote.enteties.UserServerModel
import retrofit2.http.GET

interface UserService {

    @GET("/api")
    suspend fun user(): UserServerModel
}