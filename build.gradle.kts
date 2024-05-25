// Copyright (C) 2023-2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0

plugins {
    kotlin("jvm") version Version.Plugin.KotlinJvm
    id("io.ktor.plugin") version Version.Plugin.Ktor
    id("org.flywaydb.flyway") version Version.Plugin.Flyway
}

buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:${Version.Plugin.Flyway}")
    }
}

group = ProjectMetadata.GitHub.GroupId
version = ProjectMetadata.Build.Version

repositories {
    mavenCentral()
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

flyway {
    url = BuildConfiguration.getDatabaseUrl(project)
    user = BuildConfiguration.getDatabaseUsername(project)
    password = BuildConfiguration.getDatabasePassword(project)
}

// Run any database migrations before running the web server.
tasks.getByName("run").dependsOn(tasks.getByName("flywayMigrate"))

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-html-builder")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-webjars")

    implementation("ch.qos.logback:logback-classic:${Version.Dependency.LogbackClassic}")

    implementation("org.webjars:bootstrap:${Version.Dependency.Bootstrap}")
    implementation("org.webjars.npm:bootstrap-icons:${Version.Dependency.BootstrapIcons}")

    implementation("org.webjars.npm:htmx.org:${Version.Dependency.HTMX}")
    implementation("org.webjars:swagger-ui:${Version.Dependency.SwaggerUI}")

    implementation("org.postgresql:postgresql:${Version.Dependency.PostgresJdbcDriver}")

    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${Version.Dependency.KotlinTest}")
}
