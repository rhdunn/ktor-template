// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.html

import kotlinx.html.*

fun HtmlBlockTag.sidebar(content: UL.() -> Unit) = div("d-flex flex-column p-3 bg-light sidebar") {
    ul("nav nav-pills flex-column mb-auto") {
        content()
    }
}

@Suppress("unused")
fun UL.sidebarItem(href: String, active: Boolean = false, content: A.() -> Unit) = li("nav-item") {
    a(classes = "nav-link ${if (active) "active" else ""}") {
        this.href = href
        if (active) attributes["aria-current"] = "page"
        content()
    }
}

@Suppress("unused")
fun A.sidebarIcon(icon: String) = span("bi bi-$icon me-2") {}
