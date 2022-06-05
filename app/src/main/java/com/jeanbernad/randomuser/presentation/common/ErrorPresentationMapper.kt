package com.jeanbernad.randomuser.presentation.common

import com.jeanbernad.randomuser.R
import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.core.ResourceProvider

interface ErrorPresentationMapper {
    fun map(errorType: ErrorType): String

    class Base(
        private val resourceProvider: ResourceProvider
    ) : ErrorPresentationMapper {
        override fun map(errorType: ErrorType) =
            resourceProvider.getString(
                when (errorType) {
                    ErrorType.NO_CONNECTION -> R.string.no_connection
                    ErrorType.SERVICE_UNAVAILABLE -> R.string.server_not_available
                    else -> R.string.something_go_wrong
                }
            )
    }
}