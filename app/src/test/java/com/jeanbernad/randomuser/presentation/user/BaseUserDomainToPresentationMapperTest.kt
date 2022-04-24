package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.R
import com.jeanbernad.randomuser.core.ErrorPresentationMapper
import com.jeanbernad.randomuser.core.ResourceProvider
import com.jeanbernad.randomuser.domain.ErrorType
import org.junit.Test
import org.junit.Assert.assertEquals

class BaseUserDomainToPresentationMapperTest {

    private val resourceProvider = TestResourceProvider()
    private val errorPresentationMapper = ErrorPresentationMapper.Base(resourceProvider)
    private val mapper = BaseUserDomainToPresentationMapper(errorPresentationMapper)

    @Test
    fun test_fail_no_connection() {
        val actual = mapper.map(ErrorType.NO_CONNECTION)
        val expected = UserPresentationModel.Fail("no_connection")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail_server_not_available() {
        val actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        val expected = UserPresentationModel.Fail("server_not_available")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail_something_go_wrong() {
        val actual = mapper.map(ErrorType.GENERIC)
        val expected = UserPresentationModel.Fail("something_go_wrong")
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection -> "no_connection"
            R.string.server_not_available -> "server_not_available"
            else -> "something_go_wrong"
        }
    }
}