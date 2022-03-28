package com.jeanbernad.randomuser.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T> performGetOperation(call: suspend () -> Resource<T>):
        LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val resourceStatus = call.invoke()
            this.emit(resourceStatus)
        }

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val source = databaseQuery.invoke().map { Resource.success(it) }
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
            emitSource(source)
        }