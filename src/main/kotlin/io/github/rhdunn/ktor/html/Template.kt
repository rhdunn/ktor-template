// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.html

import io.ktor.server.application.*
import io.ktor.server.html.*
import kotlinx.coroutines.runBlocking
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

const val APPLICATION_TITLE = "Ktor Template"

suspend fun ApplicationCall.respondHtmlTemplate(body: suspend MAIN.() -> Unit) = respondHtml {
    head {
        title { +APPLICATION_TITLE }
        link(rel = "stylesheet", href = "/css/default.css")
        script(src = "/modules/htmx.org/htmx.min.js") {}
    }
    body {
        header {
            h1 { +APPLICATION_TITLE }
        }
        main {
            runBlocking {
                newSuspendedTransaction {
                    this@main.body()
                }
            }
        }
    }
}
