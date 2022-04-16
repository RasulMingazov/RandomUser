package com.jeanbernad.randomuser.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jeanbernad.randomuser.data.enteties.User
import com.jeanbernad.randomuser.data.repository.UserRepository
import com.jeanbernad.randomuser.ui.base.BaseViewModel
import com.jeanbernad.randomuser.utils.Resource

class UserViewModel<T> constructor(
    private val repository: UserRepository
) : BaseViewModel<T>() {

    private val _user: LiveData<Resource<User>> = Transformations.switchMap(reloadTrigger) {
        repository.getUser()
    }
    val user = _user

    init {
        refresh()
    }
}