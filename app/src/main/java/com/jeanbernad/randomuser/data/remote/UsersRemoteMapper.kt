package com.jeanbernad.randomuser.data.remote

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.ToUserMapper
import com.jeanbernad.randomuser.data.UserData

interface UsersRemoteMapper : Abstract.Mapper {
    fun map(userRemote: List<Abstract.Object<UserData, ToUserMapper>>): List<UserData>

    class Base(private val toUserMapper: ToUserMapper) : UsersRemoteMapper {
        override fun map(userRemote: List<Abstract.Object<UserData, ToUserMapper>>) =
            userRemote.map {
                it.map(toUserMapper)
            }
    }
}