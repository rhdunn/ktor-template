// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.html

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.html.TagConsumer
import kotlinx.html.stream.createHTML

suspend fun ApplicationCall.respondHtmlFragment(content: TagConsumer<String>.() -> Unit) {
    val html = createHTML()
    html.content()
    respondText(text = html.finalize(), ContentType.Text.Html)
}
