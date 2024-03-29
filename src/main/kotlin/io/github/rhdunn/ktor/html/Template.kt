// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.html

import io.ktor.server.application.*
import io.ktor.server.html.*
import kotlinx.html.*

const val APPLICATION_TITLE = "Ktor Template"

suspend fun ApplicationCall.respondHtmlTemplate(body: MAIN.() -> Unit) = respondHtml {
    head {
        title { +APPLICATION_TITLE }
        link(rel = "stylesheet", href = "/css/default.css")
        link(rel = "stylesheet", href = "/modules/bootstrap/css/bootstrap.min.css")
        link(rel = "stylesheet", href = "/modules/bootstrap-icons/font/bootstrap-icons.min.css")
        script(src = "/modules/htmx.org/htmx.min.js") {}
    }
    body {
        header {
            h1 { +APPLICATION_TITLE }
        }
        main {
            this.body()
        }
        script(src = "/modules/bootstrap/js/bootstrap.bundle.min.js") {}
    }
}
