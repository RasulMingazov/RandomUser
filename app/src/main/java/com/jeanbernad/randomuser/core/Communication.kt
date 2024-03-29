package com.jeanbernad.randomuser.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication<T: Abstract.PresentationModel> : Abstract.Mapper.Data<T, Unit> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    abstract class Base<T : Abstract.PresentationModel> : Communication<T> {
        private val listLiveData = MutableLiveData<T>()

        override fun map(data: T) {
            listLiveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            listLiveData.observe(owner, observer)
        }
    }
}