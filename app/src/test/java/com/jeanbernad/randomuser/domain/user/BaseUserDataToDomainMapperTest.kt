package com.jeanbernad.randomuser.domain.user

import com.jeanbernad.randomuser.domain.common.ErrorDomainMapper
import com.jeanbernad.randomuser.core.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class BaseUserDataToDomainMapperTest {

    private val errorDomainMapper = ErrorDomainMapper.Base()
    private val mapper = BaseUserDataToDomainMapper(errorDomainMapper)

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
            "11.07.1945",
            "https://randomuser.me/api/portraits/women/88.jpg"
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
            "11.07.1945",
            "https://randomuser.me/api/portraits/women/88.jpg"
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