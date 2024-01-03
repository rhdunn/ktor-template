// Copyright (C) 2023-2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0

import io.github.rhdunn.ktor.swagger.swaggerUI
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.webjars.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(Webjars) {
        path = "/modules"
    }

    routing {
        get("/") { index() }
        staticResources("/css", "assets.css")
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.index() = call.respondHtml {
    head {
        title { +"Ktor template" }
        link(rel = "stylesheet", href = "/css/default.css")
    }
    body {
        p { +"Ktor template" }
    }
}
