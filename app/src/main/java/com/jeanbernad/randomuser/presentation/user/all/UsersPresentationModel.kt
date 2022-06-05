package com.jeanbernad.randomuser.presentation.user.all

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel

sealed class UsersPresentationModel : Abstract.PresentationObject<Unit, ToUsersValueMapper> {
    override fun map(mapper: ToUsersValueMapper) = Unit

    override fun textValue() = String()

    object Progress : UsersPresentationModel()

    object Empty : UsersPresentationModel() {
        override fun map(mapper: ToUsersValueMapper) {
            return mapper.map()
        }
    }

    data class Base(
        private val users: List<UserPresentationModel>
    ) : UsersPresentationModel() {
        override fun map(mapper: ToUsersValueMapper) {
            return mapper.map(users)
        }
    }
}