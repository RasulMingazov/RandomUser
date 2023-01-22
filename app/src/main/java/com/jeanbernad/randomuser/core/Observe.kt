package com.jeanbernad.randomuser.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Observe<T: Abstract.PresentationModel> {
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}