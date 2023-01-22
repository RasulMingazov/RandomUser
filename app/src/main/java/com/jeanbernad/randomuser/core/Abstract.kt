package com.jeanbernad.randomuser.core

abstract class Abstract {
    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface PresentationModel

    interface PresentationObject<T, M: Mapper>: Object<T, M>, PresentationModel

    interface PresentationModelBind

    interface DataObject

    interface Mapper {
        class Empty : Mapper

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }
    }
}