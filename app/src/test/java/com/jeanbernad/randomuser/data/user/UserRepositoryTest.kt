package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.data.user.remote.UserRemoteModel
import com.jeanbernad.randomuser.data.user.remote.entity.result.*
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Coordinates
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Location
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Street
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Timezone
import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.domain.user.BaseUserDataToDomainMapper
import com.jeanbernad.randomuser.domain.user.UserDomain
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

class UserRepositoryTest {

    @Test
    fun remote_success() = runBlocking {
        val testCloudDataSource = TestRemoteDataSource(true)
        val repository = BaseUserRepository(
            testCloudDataSource,
            ToUserMapper.Base(),
            mapper
        )
        val actual = repository.user()
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
        assertEquals(expected, actual)
    }

    @Test
    fun remote_fail() = runBlocking {
        val testCloudDataSource = TestRemoteDataSource(false)
        val repository = BaseUserRepository<UserDomain>(
            testCloudDataSource,
            ToUserMapper.Base(),
            mapper
        )
        val actual = repository.user()
        val expected = UserDomain.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)
    }

    private val mapper = BaseUserDataToDomainMapper()

    private inner class TestRemoteDataSource(
        private val isSuccess: Boolean
    ) : UserRemoteDataSource {
        override suspend fun user() =
            if (isSuccess) {
                userRemoteModel
            } else {
                throw UnknownHostException()
            }
    }

    private val userRemoteModel = UserRemoteModel(
        Id("name", "test_id"),
        "043-800-99-40",
        Dob(77, "1945-07-11T12:20:09.623Z"),
        "matilda.pollari@example.com",
        "Female",
        Location(
            "Multia",
            Coordinates("-12.0643", "53.7063"),
            "Finland",
            "94755",
            "North Karelia",
            Street("Satakennankatu", 3173),
            Timezone("Bangkok, Hanoi, Jakarta", "+7:00")
        ),
        Login(
            "b34ef87b02e4ab76b78af97aa78535f7",
            "santos", "mmvBsXVb",
            "8a98d7cabcb6c43aea339cfcdc7eeca27a2cef3c",
            "b009e19a747a7bf0897af1f93a1de7a94cc5004e299ac803d35a395a1770513e",
            "angryfrog585", "8b6d5c85-f335-474c-80c0-580def12027d"
        ),
        Name( "Pollari", "Matilda", "Mrs"),
        "FI",
        "09-010-148",
        Picture(
            "https://randomuser.me/api/portraits/women/88.jpg",
            "https://randomuser.me/api/portraits/med/women/88.jpg",
            "https://randomuser.me/api/portraits/thumb/women/88.jpg"
        ),
        Registered(6, "2016-12-30T04:39:05.109Z")

    )
}