package com.handler.demo.error.utilities

import com.handler.demo.error.exceptions.DuplicatedResourceException
import com.handler.demo.error.exceptions.ResourceNotFoundException

fun <T> Iterable<T>.singleOrThrowResourceNotFound(message: String): T {
    return when (this) {
        is List -> this.single()
        else -> {
            val iterator = iterator()
            if (!iterator.hasNext())
                throw ResourceNotFoundException(message)
            val single = iterator.next()
            if (iterator.hasNext())
                throw DuplicatedResourceException("Duplicated element")
            single
        }
    }
}