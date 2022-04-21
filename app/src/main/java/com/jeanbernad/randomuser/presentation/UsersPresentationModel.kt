package com.jeanbernad.randomuser.presentation

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel

sealed class UsersPresentationModel : Abstract.Object<Unit, UserCommunication> {

    data class Base(
        private val users: List<UserPresentationModel>
    ) : UsersPresentationModel() {
        override fun map(mapper: UserCommunication) = mapper.map(users)
    }
}

