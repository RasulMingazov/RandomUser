package com.jeanbernad.randomuser.data.remote.enteties

import com.jeanbernad.randomuser.y_data_old.enteties.info.Info
import com.jeanbernad.randomuser.y_data_old.enteties.result.Result

data class UserServerModel(
    val info: Info,
    val results: MutableList<Result>
)