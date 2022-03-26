package com.jeanbernad.randomuser.ui.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.jeanbernad.randomuser.data.enteties.User
import com.jeanbernad.randomuser.data.repository.UserRepository
import com.jeanbernad.randomuser.ui.base.BaseViewModel
import com.jeanbernad.randomuser.utils.Resource

class UserViewModel<T> @ViewModelInject constructor(
        private val repository: UserRepository
) : BaseViewModel<T>() {

    private val _localId = MutableLiveData<Int>()

    private val _user = _localId.switchMap {
        repository.getUser(it)
    }
    val user: LiveData<Resource<User>> =  repository.getUser(0)


}