package com.jeanbernad.randomuser.data.user.remote.entity.info

data class Info(
        val page: Int,
        val results: Int,
        val seed: String,
        val version: String
)