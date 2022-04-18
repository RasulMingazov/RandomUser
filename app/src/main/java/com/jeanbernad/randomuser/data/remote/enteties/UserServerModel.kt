package com.jeanbernad.randomuser.data.remote.enteties

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.UserData
import com.jeanbernad.randomuser.data.remote.UserServerToDataMapper
import com.jeanbernad.randomuser.y_data_old.enteties.info.Info
import com.jeanbernad.randomuser.y_data_old.enteties.result.Result

data class UserServerModel(
    val info: Info,
    val results: MutableList<Result>
) : Abstract.Object<UserData, UserServerToDataMapper> {
    override fun map(mapper: UserServerToDataMapper): UserData =
        mapper.map(results[0].id.value)
}