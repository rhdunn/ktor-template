// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.exposed.tests

import org.h2.jdbcx.JdbcDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

interface TestDbTransaction {
    val tables: Array<Table>

    fun withMemoryDatabase(block: Database.() -> Unit) {
        val source = JdbcDataSource()
        source.setUrl("jdbc:h2:mem:;DATABASE_TO_LOWER=TRUE")

        // Keep a connection open for the duration of the test so that H2 does
        // not delete the in-memory database.
        source.connection.use {
            val database = Database.connect(source)
            transaction(database) {
                SchemaUtils.create(*tables)
            }
            database.block()
        }
    }

    fun transaction(block: () -> Unit) = withMemoryDatabase {
        transaction(this) {
            block()
        }
    }
}
