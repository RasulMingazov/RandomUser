package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.ErrorDomainMapper
import com.jeanbernad.randomuser.data.user.remote.UserRemoteModel
import com.jeanbernad.randomuser.data.user.remote.entity.result.*
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Coordinates
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Location
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Street
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Timezone
import com.jeanbernad.randomuser.core.ErrorType
import com.jeanbernad.randomuser.data.user.local.ToUserLocalMapper
import com.jeanbernad.randomuser.data.user.local.UserLocalModel
import com.jeanbernad.randomuser.domain.user.BaseUserDataToDomainMapper
import com.jeanbernad.randomuser.domain.user.UserDomain
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

class UserRepositoryTest {

    private val unknownHostException = UnknownHostException()
    private val errorDomainMapper = ErrorDomainMapper.Base()
    private val mapper = BaseUserDataToDomainMapper(errorDomainMapper)

    @Test
    fun user_remote_success_local_success() = runBlocking {
        val testCloudDataSource = TestRemoteDataSource(true)
        val testLocalDataSource = TestLocalDataSource(true, isEmpty = false)
        val repository = BaseUserRepository(
            testCloudDataSource,
            testLocalDataSource,
            ToUserMapper.Base(),
            ToUserLocalMapper.Base(),
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
    fun user_remote_fail_local_success() = runBlocking {
        val testCloudDataSource = TestRemoteDataSource(false)
        val testLocalDataSource = TestLocalDataSource(true, isEmpty = false)
        val repository = BaseUserRepository(
            testCloudDataSource,
            testLocalDataSource,
            ToUserMapper.Base(),
            ToUserLocalMapper.Base(),
            mapper
        )
        val actual = repository.user()
        val expected = UserDomain.Success(
            "Mr James Abraham",
            "A street",
            "Male",
            "043-800-99-41",
            "matilda.pollari@example.com",
            "England",
            "London",
            "(-12.064353, 7063)",
            "23.04.2001",
            "https://randomuser.me/api/portraits/women/88.jpg",
        )
        assertEquals(expected, actual)
    }

    @Test
    fun user_remote_success_local_fail() = runBlocking {
        val testCloudDataSource = TestRemoteDataSource(true)
        val testLocalDataSource = TestLocalDataSource(false, isEmpty = false)
        val repository = BaseUserRepository(
            testCloudDataSource,
            testLocalDataSource,
            ToUserMapper.Base(),
            ToUserLocalMapper.Base(),
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
    fun user_remote_fail_local_fail() = runBlocking {
        val testCloudDataSource = TestRemoteDataSource(false)
        val testLocalDataSource = TestLocalDataSource(false, isEmpty = true)
        val repository = BaseUserRepository(
            testCloudDataSource,
            testLocalDataSource,
            ToUserMapper.Base(),
            ToUserLocalMapper.Base(),
            mapper
        )

        val actual = repository.user()
        print(actual.toString())
        val expected = UserDomain.Fail(ErrorType.NO_CONNECTION)

        assertEquals(expected, actual)
    }

    private inner class TestRemoteDataSource(
        private val isSuccess: Boolean
    ) : UserRemoteDataSource {
        override suspend fun user() =
            if (isSuccess) {
                userRemoteModel
            } else {
                throw unknownHostException
            }
    }

    private inner class TestLocalDataSource(
        private val isSuccess: Boolean,
        private val isEmpty: Boolean
    ) : UserLocalDataSource {
        override suspend fun user() =
            if (isSuccess) {
                userLocalListModel[0]
            } else {
                throw unknownHostException
            }

        override suspend fun allUsers() =
            if (isSuccess)
                userLocalListModel
            else
                emptyList()


        override suspend fun insert(userLocalModel: UserLocalModel) {
        }

        override suspend fun countUsers() =
            if (isEmpty) 0 else 1
    }

    private val userLocalListModel =
        listOf(
            UserLocalModel(
                "Mr James Abraham",
                "23.04.2001",
                "Male",
                "043-800-99-41",
                "matilda.pollari@example.com",
                "England",
                "London",
                "A street",
                "(-12.064353, 7063)",
                "https://randomuser.me/api/portraits/women/88.jpg",
            ),
            UserLocalModel(
                "Mr Michael Henry",
                "3.04.2002",
                "Male",
                "043-800-93-01",
                "cwcwqa.pollari@example.com",
                "England",
                "London",
                "A street 2",
                "(-12.064353, 7132)",
                "https://randomuser.me/api/portraits/women/89.jpg",
            )
        )

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
        Name("Pollari", "Matilda", "Mrs"),
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