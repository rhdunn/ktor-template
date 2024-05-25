// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.exposed.dao

import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import java.sql.ResultSet
import kotlin.reflect.KClass

abstract class WrappedColumnType<T : Any, BaseT>(
    private val baseType: ColumnType<BaseT>,
    private val klass: KClass<*>
) : ColumnType<T>() {
    abstract override fun valueFromDB(value: Any): T

    abstract override fun valueToDB(value: T?): Any?

    override fun sqlType(): String = baseType.sqlType()

    override fun readObject(rs: ResultSet, index: Int): Any? {
        val value = baseType.readObject(rs, index)
        return if (value != null) {
            valueFromDB(value)
        } else {
            null
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) = when {
        klass.isInstance(value) -> super.setParameter(stmt, index, valueToDB(value as T))
        else -> super.setParameter(stmt, index, value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun nonNullValueToString(value: T): String {
        return baseType.nonNullValueToString(valueToDB(value) as (BaseT & Any))
    }

    fun wrappedFromDB(value: Any): BaseT {
        return baseType.valueFromDB(value)!!
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as WrappedColumnType<*, *>

        return baseType == other.baseType
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + baseType.hashCode()
        return result
    }
}
