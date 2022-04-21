package com.jeanbernad.randomuser.data.user.remote

import com.jeanbernad.randomuser.data.user.remote.entity.info.Info

data class UserResponse(
    val info: Info,
    val results: MutableList<UserRemoteModel>
)