// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.application

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Suppress("unused")
suspend inline fun ApplicationCall.handleRequest(
    crossinline block: suspend Transaction.() -> Unit
) = newSuspendedTransaction {
    block()
    if (response.status()!!.value >= 400) rollback()
}
