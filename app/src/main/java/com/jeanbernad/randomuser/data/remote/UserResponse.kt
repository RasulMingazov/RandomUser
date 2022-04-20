package com.jeanbernad.randomuser.data.remote

import com.jeanbernad.randomuser.data.remote.entity.info.Info

data class UserResponse(
    val info: Info,
    val results: MutableList<UserRemoteModel>
)
