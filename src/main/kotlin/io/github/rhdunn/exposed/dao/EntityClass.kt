// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.exposed.dao

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Op

fun <ID : Comparable<ID>, T : Entity<ID>> EntityClass<ID, T>.findOrNew(op: Op<Boolean>, init: T.() -> Unit): T {
    return find(op).firstOrNull() ?: try {
        new(init)
    } catch (e: ExposedSQLException) {
        // The entity has been added between inserts, or this is a different error.
        find(op).firstOrNull() ?: throw e
    }
}
