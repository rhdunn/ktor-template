// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.application

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

suspend fun ApplicationCall.respondJson(json: String, status: HttpStatusCode = HttpStatusCode.OK) {
    respondText(json, contentType = ContentType.Application.Json, status = status)
}

@Suppress("unused")
suspend fun ApplicationCall.respondJson(json: JsonObject, status: HttpStatusCode = HttpStatusCode.OK) {
    respondJson(json.toString(), status)
}

@Suppress("unused")
suspend fun ApplicationCall.respondJson(json: JsonArray, status: HttpStatusCode = HttpStatusCode.OK) {
    respondJson(json.toString(), status)
}
