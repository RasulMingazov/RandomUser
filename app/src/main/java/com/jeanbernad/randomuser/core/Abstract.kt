package com.jeanbernad.randomuser.core

abstract class Abstract {

    interface DataObject

    interface CloudObject

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface Mapper {

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(e: Exception): R
        }

        interface DomainToUi<S, T> : Data<S, T> {
            fun map(errorType: ErrorType): T
        }

        class Empty : Mapper
    }
}