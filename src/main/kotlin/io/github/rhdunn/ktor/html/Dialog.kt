// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.html

import io.ktor.server.application.*
import kotlinx.html.*

@Suppress("unused")
inline fun FlowOrInteractiveOrPhrasingContent.dialogButton(
    classes: String? = null,
    href: String,
    crossinline block: BUTTON.() -> Unit,
) = button(classes = classes) {
    attributes["hx-get"] = href
    attributes["hx-trigger"] = "click"
    attributes["hx-target"] = "#modal-target"
    attributes["data-bs-toggle"] = "modal"
    attributes["data-bs-target"] = "#modal-target"
    block()
}

@Suppress("unused")
suspend fun ApplicationCall.respondModalDialog(
    classes: String? = null,
    block: DIV.() -> Unit,
) = respondHtmlFragment {
    div(classes = "modal-dialog modal-dialog-centered ${classes ?: ""}") {
        div(classes = "modal-content", block)
    }
}
