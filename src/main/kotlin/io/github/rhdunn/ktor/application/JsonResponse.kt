// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.application

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.serialization.json.*

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

suspend fun ApplicationCall.respondJsonLd(json: String, status: HttpStatusCode = HttpStatusCode.OK) {
    respondText(json, contentType = JsonLd, status = status)
}

@Suppress("unused")
suspend fun ApplicationCall.respondJsonLd(json: JsonObject, status: HttpStatusCode = HttpStatusCode.OK) {
    respondJsonLd(json.toString(), status)
}

@Suppress("unused")
suspend fun ApplicationCall.respondJsonLd(json: JsonArray, status: HttpStatusCode = HttpStatusCode.OK) {
    respondJsonLd(json.toString(), status)
}

@Suppress("unused")
fun buildSchemaObject(
    type: String,
    isRoot: Boolean = false,
    builder: JsonObjectBuilder.() -> Unit
): JsonObject = buildJsonObject {
    if (isRoot) {
        put("@context", JsonPrimitive("https://schema.org"))
    }
    put("@type", JsonPrimitive(type))
    this.builder()
}

private val JsonLd = ContentType.parse("application/ld+json")
