// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.html

import io.ktor.server.application.*
import io.ktor.server.html.*
import kotlinx.html.*

const val APPLICATION_TITLE = "Ktor Template"

suspend fun ApplicationCall.respondHtmlTemplate(
    classes: String? = "p-2",
    body: MAIN.() -> Unit
) = respondHtml {
    head {
        title { +APPLICATION_TITLE }
        link(rel = "stylesheet", href = "/css/default.css")
        link(rel = "stylesheet", href = "/modules/bootstrap/css/bootstrap.min.css")
        link(rel = "stylesheet", href = "/modules/bootstrap-icons/font/bootstrap-icons.min.css")
        script(src = "/modules/htmx.org/htmx.min.js") {}
    }
    body {
        nav("navbar navbar-expand-lg bg-body-tertiary") {
            div("container-fluid") {
                a(classes = "navbar-brand", href = "/") { +APPLICATION_TITLE }
            }
        }
        main(classes) {
            this.body()
        }
        script(src = "/modules/bootstrap/js/bootstrap.bundle.min.js") {}
        div(classes = "modal modal-blur fade") {
            id = "modal-target"
            attributes["style"] = "display: none;"
            attributes["aria-hidden"] = "false"
            tabIndex = "-1"
            div(classes = "modal-dialog modal-lg modal-dialog-centered") {
                role = "document"
                div(classes = "modal-content")
            }
        }
    }
}
