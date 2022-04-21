package com.jeanbernad.randomuser.presentation

import com.jeanbernad.randomuser.core.Abstract

sealed class UserPresentationModel : Abstract.Object<Unit, UserPresentationModel.StringMapper> {
    override fun map(mapper: StringMapper) = Unit

    object Progress : UserPresentationModel()
    class Base(
        private val id: String
        ) : UserPresentationModel() {
        override fun map(mapper: StringMapper) = mapper.map(id = id)
    }

    data class Fail(private val message: String) : UserPresentationModel() {
        override fun map(mapper: StringMapper) = mapper.map(errorMessage = message, false)
    }

    interface StringMapper : Abstract.Mapper {
        fun map(id: String)
        // TODO fix later
        fun map(errorMessage: String, dummy: Boolean)
    }
}