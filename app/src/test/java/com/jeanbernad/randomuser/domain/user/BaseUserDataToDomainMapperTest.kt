package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.data.user.BaseUserDataToDomainMapper
import com.jeanbernad.randomuser.domain.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseUserDataToDomainMapperTest {

    private val mapper = BaseUserDataToDomainMapper()

    @Test
    fun test_success() {
        val actual = mapper.map(
            "Mrs Pollari Matilda",
            "Satakennankatu, 3173",
            "Female",
            "09-010-148",
            "matilda.pollari@example.com",
            "Finland",
            "Multia",
            "(-12.0643, 53.7063)",
            "11.07.1945"
        )

        val expected = UserDomain.Success(
            "Mrs Pollari Matilda",
            "Satakennankatu, 3173",
            "Female",
            "09-010-148",
            "matilda.pollari@example.com",
            "Finland",
            "Multia",
            "(-12.0643, 53.7063)",
            "11.07.1945"
        )
        assertEquals(actual, expected)
    }

    @Test
    fun test_fail() {
        var actual = mapper.map(UnknownHostException())
        var expected = UserDomain.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)
        actual = mapper.map(IllegalStateException())
        expected = UserDomain.Fail(ErrorType.GENERIC)
        assertEquals(expected, actual)
    }
}