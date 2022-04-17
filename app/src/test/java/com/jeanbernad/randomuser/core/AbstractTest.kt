package com.jeanbernad.randomuser.core

import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception

class AbstractTest {

    @Test
    fun test_success() {
        val dataObject = DataObject.Success(2, 3)
        val domainObject = dataObject.map(DataToDomainMapper.Base())

        val expected = DomainObject.Success(6)
        assertEquals(expected, domainObject)
    }

    @Test
    fun test_error() {

        val dataObject = DataObject.Fail(NullPointerException())
        val domainObject = dataObject.map(DataToDomainMapper.Base())

        assertTrue(domainObject is DomainObject.Fail)

    }
    sealed class DataObject : Abstract.Object<DomainObject, DataToDomainMapper> {
        abstract override fun map(mapper: DataToDomainMapper): DomainObject

        class Success(
            private val firstNumber: Int,
            private val secondNumber: Int
        ) : DataObject() {
            override fun map(mapper: DataToDomainMapper): DomainObject {
                return mapper.map(firstNumber, secondNumber)
            }
        }

        class Fail(private val exception: Exception) : DataObject() {
            override fun map(mapper: DataToDomainMapper): DomainObject {
                return mapper.map(exception)
            }
        }
    }

    interface DataToDomainMapper : Abstract.Mapper.Data<Exception, DomainObject> {

        fun map(firstNumber: Int, secondNumber: Int): DomainObject

        class Base : DataToDomainMapper {
            override fun map(firstNumber: Int, secondNumber: Int): DomainObject =
                DomainObject.Success(firstNumber * secondNumber)

            override fun map(data: Exception): DomainObject = DomainObject.Fail(
                when (data) {
                    is java.lang.NullPointerException -> ErrorType.GENERIC
                    else -> ErrorType.SERVICE_UNAVAILABLE
                }
            )


        }
    }

    sealed class DomainObject : Abstract.Object<PresentationObject, DomainToPresentationMapper> {

        data class Success(private val resultOfMultiply: Int) : DomainObject() {
            override fun map(mapper: DomainToPresentationMapper): PresentationObject {
                throw IllegalStateException("not implemented yet")
            }
        }

        data class Fail(private val errorType: ErrorType) : DomainObject() {
            override fun map(mapper: DomainToPresentationMapper): PresentationObject =
                throw IllegalStateException("not implemented yet")

        }
    }

    interface DomainToPresentationMapper : Abstract.Mapper

    sealed class PresentationObject : Abstract.Object<Unit, Abstract.Mapper.Empty>
}