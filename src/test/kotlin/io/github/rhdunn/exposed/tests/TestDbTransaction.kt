// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.exposed.tests

import org.h2.jdbcx.JdbcDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

// The H2 in-memory database is destroyed when a connection is closed, so only
// make the outermost connection closable.
private class InMemoryConnection(connection: Connection) : Connection by connection {
    override fun close() {}
}

interface TestDbTransaction {
    val tables: Array<Table>

    fun withMemoryDatabase(block: Database.() -> Unit) {
        val source = JdbcDataSource()
        source.setUrl("jdbc:h2:mem:;DATABASE_TO_LOWER=TRUE")
        source.connection.use {
            val database = Database.connect(getNewConnection = { InMemoryConnection(it) })
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
