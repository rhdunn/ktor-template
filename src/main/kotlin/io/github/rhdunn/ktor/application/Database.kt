// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.application

import io.ktor.server.application.*
import org.postgresql.ds.PGConnectionPoolDataSource
import org.postgresql.ds.PGSimpleDataSource
import javax.sql.ConnectionPoolDataSource
import javax.sql.DataSource

val ApplicationEnvironment.databaseUrl: String?
    get() = config.propertyOrNull("database.url")?.getString()

val ApplicationEnvironment.databaseUsername: String?
    get() = config.propertyOrNull("database.username")?.getString()

val ApplicationEnvironment.databasePassword: String?
    get() = config.propertyOrNull("database.password")?.getString()

val Application.dataSource: DataSource
    get() {
        val source = PGSimpleDataSource()
        source.setUrl(environment.databaseUrl)
        source.user = environment.databaseUsername
        source.password = environment.databasePassword
        return source
    }

@Suppress("unused")
val Application.connectionPoolDataSource: ConnectionPoolDataSource
    get() {
        val source = PGConnectionPoolDataSource()
        source.setUrl(environment.databaseUrl)
        source.user = environment.databaseUsername
        source.password = environment.databasePassword
        return source
    }
