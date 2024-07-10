// Copyright (C) 2023-2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0

import io.github.rhdunn.ktor.application.dataSource
import io.github.rhdunn.ktor.html.respondHtmlTemplate
import io.github.rhdunn.ktor.swagger.swaggerUI
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import io.ktor.server.webjars.*
import io.ktor.util.pipeline.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.Database

@Suppress("unused")
fun Application.module() {
    Database.connect(dataSource)

    install(Webjars) {
        path = "/modules"
    }

    routing {
        get("/") { index() }

        staticResources("/css", "assets.css")
        staticResources("/js", "assets.js")

        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.index() = call.respondHtmlTemplate {
    p { +"Ktor template" }
}
