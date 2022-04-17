package com.jeanbernad.randomuser.y_data_old.remote

import android.util.Log
import com.jeanbernad.randomuser.y_utils.Resource
import retrofit2.Response

abstract class DataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Log.d("DataSource", "Success")
                    return Resource.success(body)
                }
            }
            Log.d("DataSource", " ${response.code()} ${response.message()}")
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.d("DataSource", "Network call has failed for a following reason: $message")
        return Resource.error("Network call has failed for a following reason: $message")
    }
}