// Copyright (C) 2023 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0

plugins {
    kotlin("jvm") version Version.Plugin.KotlinJvm
    id("io.ktor.plugin") version Version.Plugin.Ktor
}

group = ProjectMetadata.GitHub.GroupId
version = ProjectMetadata.Build.Version

repositories {
    mavenCentral()
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-html-builder")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-webjars")

    implementation("ch.qos.logback:logback-classic:${Version.Dependency.LogbackClassic}")

    implementation("org.webjars.npm:htmx.org:${Version.Dependency.HTMX}")
    implementation("org.webjars:swagger-ui:${Version.Dependency.SwaggerUI}")

    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${Version.Dependency.KotlinTest}")
}
