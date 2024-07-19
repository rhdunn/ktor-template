// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.swagger

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Routing.swaggerUI(path: String, swaggerFile: String) = route(path) {
    val apiUrl = swaggerFile.takeLastWhile { it != '/' }
    get(apiUrl) {
        val swagger = this.javaClass.classLoader.getResourceAsStream(swaggerFile)
        call.respondOutputStream(ContentType.fromFilePath(swaggerFile).firstOrNull()) {
            swagger?.copyTo(this)
        }
    }
    get { call.swaggerUI(apiUrl) }
}

suspend fun ApplicationCall.swaggerUI(apiUrl: String) = respondHtml {
    head {
        title { +"Swagger UI" }
        link(rel = "stylesheet", href = "/modules/swagger-ui/swagger-ui.css")
        link(rel = "stylesheet", href = "/css/swagger.css")
    }
    body {
        div { id = "swagger-ui" }
        script(src = "/modules/swagger-ui/swagger-ui-bundle.js") {}
        script(src = "/modules/swagger-ui/swagger-ui-standalone-preset.js") {}
        script {
            unsafe {
                +"""
                window.onload = function() {
                    window.ui = SwaggerUIBundle({
                        url: '${request.path()}/$apiUrl',
                        dom_id: '#swagger-ui',
                        validatorUrl: 'none',
                        presets: [
                        SwaggerUIBundle.presets.apis,
                        SwaggerUIStandalonePreset
                        ],
                        layout: 'StandaloneLayout'
                    });
                }
                """.trimIndent()
            }
        }
    }
}
