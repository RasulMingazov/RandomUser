package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.data.user.remote.UserRemoteModel
import com.jeanbernad.randomuser.data.user.remote.entity.result.Id
import com.jeanbernad.randomuser.domain.ErrorType
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
        val expected = UserDomain.Success("test_id")
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
                UserRemoteModel(Id("name", "test_id"))
            } else {
                throw UnknownHostException()
            }
    }
}