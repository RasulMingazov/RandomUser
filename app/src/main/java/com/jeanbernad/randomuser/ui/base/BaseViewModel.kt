package com.jeanbernad.randomuser.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeanbernad.randomuser.utils.Resource

abstract class BaseViewModel<T> : ViewModel() {

    val reloadTrigger = MutableLiveData<Boolean>()

    fun refresh() {
        reloadTrigger.value = true
    }

    fun bind(resource: Resource<T>, success: () -> Unit, error: () -> Unit, loading: () -> Unit) {
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