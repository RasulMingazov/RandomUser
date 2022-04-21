package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.Abstract
import java.lang.Exception

interface ToUserMapper : Abstract.Mapper {
    fun map(id: String): UserData.Success
    fun map(exception: Exception): UserData.Fail

    class Base : ToUserMapper {
        override fun map(id: String) = UserData.Success(id)
        override fun map(exception: Exception) = UserData.Fail(exception)
    }
}