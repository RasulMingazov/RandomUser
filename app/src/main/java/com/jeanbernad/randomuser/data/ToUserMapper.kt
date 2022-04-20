package com.jeanbernad.randomuser.data

import com.jeanbernad.randomuser.core.Abstract

interface ToUserMapper : Abstract.Mapper {
    fun map(id: String): UserData

    class Base : ToUserMapper {
        override fun map(id: String) = UserData.Base(id)
    }
}