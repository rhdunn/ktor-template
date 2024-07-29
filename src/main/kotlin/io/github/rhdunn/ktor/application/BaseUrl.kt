// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.application

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*

@Suppress("unused")
val ApplicationCall.baseUrl: String get() = request.origin.serverBaseUrl

val RequestConnectionPoint.serverBaseUrl: String
    get() = when {
        serverPort == defaultPort(scheme) -> "$scheme://$serverHost"
        else -> "$scheme://$serverHost:$serverPort"
    }

@Suppress("unused")
val RequestConnectionPoint.localBaseUrl: String
    get() = when {
        localPort == defaultPort(scheme) -> "$scheme://$localHost"
        else -> "$scheme://$localHost:$localPort"
    }

private fun defaultPort(scheme: String): Int = when (scheme) {
    "http" -> 80
    "https" -> 443
    else -> -1
}
