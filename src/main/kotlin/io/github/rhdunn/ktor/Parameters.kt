// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor

import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.util.converters.*
import io.ktor.util.reflect.*

@Suppress("NOTHING_TO_INLINE", "unused")
inline fun Parameters.getOrNull(name: String): String? {
    return get(name)?.takeIf { it.isNotEmpty() }
}

@Suppress("unused")
inline fun <reified R : Any> Parameters.getOrNull(name: String): R? {
    return getOrNullImpl(name, typeInfo<R>())
}

@PublishedApi
internal fun <R : Any> Parameters.getOrNullImpl(name: String, typeInfo: TypeInfo): R? {
    val values = getAll(name)
        ?.filter { it.isNotEmpty() }
        ?.takeIf { it.isNotEmpty() } ?: return null
    return try {
        @Suppress("UNCHECKED_CAST")
        DefaultConversionService.fromValues(values, typeInfo) as R
    } catch (cause: Exception) {
        throw ParameterConversionException(name, typeInfo.type.simpleName ?: typeInfo.type.toString(), cause)
    }
}
