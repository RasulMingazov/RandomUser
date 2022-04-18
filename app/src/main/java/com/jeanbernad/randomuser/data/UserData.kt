package com.jeanbernad.randomuser.data

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.remote.enteties.UserServerModel
import com.jeanbernad.randomuser.domain.UserDomain

sealed class UserData: Abstract.Object<UserDomain, Abstract.Mapper.Empty> {
//    class Success(private val userServerModel: UserServerModel): UserData()
}