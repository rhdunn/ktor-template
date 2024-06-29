// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package io.github.rhdunn.ktor.tests

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.*

class TestApplicationCall : ApplicationCall {
    override lateinit var application: Application
    override lateinit var request: ApplicationRequest
    override lateinit var response: ApplicationResponse
    override lateinit var attributes: Attributes
    override lateinit var parameters: Parameters

    fun parameters(builder: ParametersBuilder.() -> Unit) {
        parameters = ParametersBuilder().apply(builder).build()
    }
}

fun testApplicationCall(builder: TestApplicationCall.() -> Unit): ApplicationCall {
    return TestApplicationCall().apply(builder)
}
