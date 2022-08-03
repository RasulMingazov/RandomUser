package com.jeanbernad.randomuser.presentation.common

interface TextOperation {
    fun combineEveryValue(vararg values: String): String

    class Base: TextOperation {
        override fun combineEveryValue(vararg values: String): String {
            return values.joinToString(separator = "\n")
        }
    }
}