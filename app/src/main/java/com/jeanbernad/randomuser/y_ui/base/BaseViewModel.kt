package com.jeanbernad.randomuser.y_ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeanbernad.randomuser.y_utils.Resource

abstract class BaseViewModel<T> : ViewModel() {

    val reloadTrigger = MutableLiveData<Boolean>()

    fun refresh() {
        reloadTrigger.value = true
    }

    inline fun bind(resource: Resource<T>, success: () -> Unit, error: () -> Unit, loading: () -> Unit) {
        when (resource.status) {
            Resource.Status.SUCCESS ->
                success()
            Resource.Status.ERROR ->
                error()
            Resource.Status.LOADING ->
                loading()
        }
    }
}