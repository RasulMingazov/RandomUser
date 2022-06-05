package com.jeanbernad.randomuser.data.user.all.local

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.user.ToUserDataMapper
import com.jeanbernad.randomuser.data.user.UserData

interface UsersLocalMapper :Abstract.Mapper.Data<List<Abstract.Object<UserData, ToUserDataMapper>>, List<UserData>>{
    class Base(private val toUserDataMapper: ToUserDataMapper) : UsersLocalMapper {
        override fun map(data: List<Abstract.Object<UserData, ToUserDataMapper>>) =
            data.map { upcomingEntity ->
                upcomingEntity.map(toUserDataMapper)
            }
    }
}