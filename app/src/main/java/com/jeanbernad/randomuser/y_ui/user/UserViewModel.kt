package com.jeanbernad.randomuser.y_ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jeanbernad.randomuser.y_data_old.enteties.User
import com.jeanbernad.randomuser.y_data_old.repository.UserRepository
import com.jeanbernad.randomuser.y_ui.base.BaseViewModel
import com.jeanbernad.randomuser.y_utils.Resource

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