package com.jeanbernad.randomuser.data.remote

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.UserData

interface UserServerToDataMapper: Abstract.Mapper {

    fun map(id: String): UserData
}