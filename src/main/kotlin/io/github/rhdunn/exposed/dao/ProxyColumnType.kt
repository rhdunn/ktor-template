// Copyright (C) 2024 Oleg Babichev. SPDX-License-Identifier: Apache-2.0
// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.exposed.dao

import org.jetbrains.exposed.sql.ColumnType
import kotlin.reflect.KClass

/**
 * @see <a href="https://youtrack.jetbrains.com/issue/EXPOSED-388">EXPOSED-388</a>
 * @author Oleg Babichev
 */
abstract class ProxyColumnType<T, S>(
    val delegate: ColumnType<S>,
    val klass: KClass<*>
) : ColumnType<T>() {
    abstract fun fromSource(source: S?): T?

    abstract fun notNullValueToSource(target: T): S & Any

    override fun sqlType() = delegate.sqlType()

    @Suppress("UNCHECKED_CAST")
    override fun valueFromDB(value: Any): T? = when {
        klass.isInstance(value) -> value as T
        else -> fromSource(delegate.valueFromDB(value))
    }

    override fun notNullValueToDB(value: T & Any): Any {
        return delegate.notNullValueToDB(notNullValueToSource(value))
    }

    override fun nonNullValueToString(value: T & Any): String {
        return delegate.nonNullValueToString(notNullValueToSource(value))
    }
}
