package com.jeanbernad.randomuser.core

abstract class Abstract {
    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface PresentationObject<T, M: Mapper>: Object<T, M> {
        fun textValue() : String
    }

    interface DataObject

    interface Mapper {
        class Empty : Mapper

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }
    }
}