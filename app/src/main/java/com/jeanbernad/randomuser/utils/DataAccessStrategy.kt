package com.jeanbernad.randomuser.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

fun <T> performGetOperation(call: suspend () -> Resource<T>):
        LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val resourceStatus = call.invoke()
        this.emit(resourceStatus)
    }