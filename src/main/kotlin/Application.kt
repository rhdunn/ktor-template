// Copyright (C) 2023 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    println("Ktor template")

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
}
