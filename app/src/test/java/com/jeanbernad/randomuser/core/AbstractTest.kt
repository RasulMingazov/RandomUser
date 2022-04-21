package com.jeanbernad.randomuser.core

import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException
import java.lang.NullPointerException

class AbstractTest {

    @Test
    fun test_1() {
        val dataObject = TestDataObject.Success(2, 3)
        val domainObject = dataObject.map(DataMapper.Base())
        assertTrue(domainObject is DomainObject.Success)
    }

    @Test
    fun test_2() {
        val dataObject = TestDataObject.Fail(NullPointerException())
        val domainObject = dataObject.map(DataMapper.Base())
        assertTrue(domainObject is DomainObject.Fail)
    }

    sealed class TestDataObject : Abstract.Object<DomainObject, DataMapper> {
        abstract override fun map(mapper: DataMapper): DomainObject

        class Success(
            private val firstValue: Int,
            private val secondValue: Int
        ) : TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(firstValue, secondValue)
            }
        }

        class Fail(private val exception: Exception) : TestDataObject() {
            override fun map(mapper: DataMapper): DomainObject {
                return mapper.map(exception)
            }
        }
    }

    interface DataMapper : Abstract.Mapper {

        fun map(firstValue: Int, secondValue: Int): DomainObject

        fun map(exception: Exception): DomainObject

        class Base : DataMapper {
            override fun map(firstValue: Int, secondValue: Int): DomainObject {
                return DomainObject.Success(firstValue * secondValue)
            }

            override fun map(exception: Exception): DomainObject {
                return DomainObject.Fail
            }
        }
    }

    sealed class DomainObject : Abstract.Object<PresentationObject, DomainToPresentationMapper> {

        data class Success(private val resultValue: Int) : DomainObject() {
            override fun map(mapper: DomainToPresentationMapper): PresentationObject {
                throw IllegalStateException("not implemented yet")
            }
        }

        object Fail : DomainObject() {
            override fun map(mapper: DomainToPresentationMapper): PresentationObject {
                throw IllegalStateException("not implemented yet")
            }
        }
    }

    interface DomainToPresentationMapper : Abstract.Mapper

    sealed class PresentationObject : Abstract.Object<Unit, Abstract.Mapper.Empty> {}
}