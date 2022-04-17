package com.jeanbernad.randomuser.y_data_old.remote

import com.jeanbernad.randomuser.y_data_old.enteties.User
import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("/api")
    suspend fun getUser(): Response<User>
}