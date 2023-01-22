package com.jeanbernad.randomuser.presentation.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.core.Communication
import com.jeanbernad.randomuser.core.Observe

interface BaseViewModel<T: Abstract.PresentationModel> : Observe<T> {

    abstract class Base<T : Abstract.PresentationModel>(
        private val communication: Communication<T>
    ) : BaseViewModel<T>, ViewModel() {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>)
                = communication.observe(owner, observer)
    }
}