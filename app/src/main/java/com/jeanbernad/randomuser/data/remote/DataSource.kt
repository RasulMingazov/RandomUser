package com.jeanbernad.randomuser.data.remote

import android.util.Log
import com.jeanbernad.randomuser.data.enteties.MinimalUser
import com.jeanbernad.randomuser.data.enteties.User
import com.jeanbernad.randomuser.utils.Resource
import retrofit2.Response


abstract class DataSource {
    protected suspend fun  getResult(call: suspend () -> Response<User>): Resource<MinimalUser> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body: User? = response.body()
                if (body != null) {
                    Log.d("DataSource", "Success")
                    return Resource.success(body.minimalUser())
                }
            }
            Log.d("DataSource",  " ${response.code()} ${response.message()}")
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.d("DataSource","Network call has failed for a following reason: $message" )
        return Resource.error("Network call has failed for a following reason: $message")
    }
}