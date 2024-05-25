// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.exposed.dao

import org.jetbrains.exposed.sql.StringColumnType
import org.jetbrains.exposed.sql.TextColumnType
import java.sql.ResultSet
import kotlin.reflect.KClass

abstract class WrappedTextColumnType<T : Any>(
    baseType: TextColumnType,
    klass: KClass<*>
) : WrappedColumnType<T, String>(baseType, klass) {
    constructor(klass: KClass<*>, collate: String? = null, eagerLoading: Boolean = false) :
            this(TextColumnType(collate, eagerLoading), klass)

    val eagerLoading: Boolean get() = (baseType as TextColumnType).eagerLoading

    fun preciseType(): String = (baseType as TextColumnType).preciseType()

    override fun readObject(rs: ResultSet, index: Int): Any? {
        val value = (StringColumnType::readObject)(baseType as TextColumnType, rs, index)
        return if (eagerLoading && value != null) {
            valueFromDB(value)
        } else {
            value
        }
    }
}
