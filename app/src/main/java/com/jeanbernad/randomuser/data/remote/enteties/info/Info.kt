package com.jeanbernad.randomuser.data.remote.enteties.info

data class Info(
        val page: Int,
        val results: Int,
        val seed: String,
        val version: String
)