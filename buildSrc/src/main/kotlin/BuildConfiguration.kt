// Copyright (C) 2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0

import org.gradle.api.Project

/**
 * Accessors for the build configuration options.
 */
@Suppress("ConstPropertyName")
object BuildConfiguration {
    private const val DatabaseUrlEnvName = "DB_URL"
    private const val DatabaseUsernameEnvName = "DB_USERNAME"
    private const val DatabasePasswordEnvName = "DB_PASSWORD"

    /**
     * The URL of the JDBC connection string for the database.
     */
    fun getDatabaseUrl(project: Project): String? {
        return getProperty(project, "db.url", DatabaseUrlEnvName)
    }

    /**
     * The user to connect to the database as.
     */
    fun getDatabaseUsername(project: Project): String? {
        return getProperty(project, "db.username", DatabaseUsernameEnvName)
    }

    /**
     * The user to connect to the database as.
     */
    fun getDatabasePassword(project: Project): String? {
        return getProperty(project, "db.password", DatabasePasswordEnvName)
    }

    private fun getProperty(project: Project, name: String, envName: String? = null): String? {
        val projectValue = project.findProperty(name)?.toString()
            ?.takeIf { value -> value.isNotBlank() }
        val systemValue = System.getProperty(name)
            ?.takeIf { value -> value.isNotBlank() }
        val envValue = envName?.let { System.getenv(it) }
            ?.takeIf { value -> value.isNotBlank() }
        return envValue ?: systemValue ?: projectValue
    }
}
