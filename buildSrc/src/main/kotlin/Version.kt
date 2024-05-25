// Copyright (C) 2023-2024 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0

/**
 * Versions of the various plugins and libraries used by the project.
 */
@Suppress("ConstPropertyName")
object Version {
    /**
     * The version of the Kotlin compiler and runtime.
     *
     * `SPDX-License-Identifier: Apache-2.0`
     *
     * @see <a href="https://github.com/JetBrains/kotlin">https://github.com/JetBrains/kotlin</a>
     */
    const val Kotlin = "1.9.21"

    /**
     * Versions of the various plugins used by the project.
     */
    object Plugin {
        /**
         * The version of the `id("io.ktor.plugin")` plugin.
         *
         * `SPDX-License-Identifier: Apache-2.0`
         *
         * @see <a href="https://github.com/ktorio/ktor">https://github.com/ktorio/ktor</a>
         */
        const val Ktor = "2.3.6"

        /**
         * The version of the `kotlin("jvm")` plugin.
         *
         * `SPDX-License-Identifier: Apache-2.0`
         *
         * @see <a href="https://github.com/JetBrains/kotlin/tree/master/libraries/tools/kotlin-gradle-plugin">https://github.com/JetBrains/kotlin/tree/master/libraries/tools/kotlin-gradle-plugin</a>
         */
        const val KotlinJvm = Kotlin

        /**
         * The version of the `id("org.flywaydb.flyway")` plugin.
         *
         * `SPDX-License-Identifier: Apache-2.0`
         *
         * @see <a href="https://github.com/flyway/flywy">https://github.com/flyway/flyway</a>
         */
        const val Flyway = "10.10.0"
    }

    /**
     * Versions of the various libraries used by the project.
     */
    object Dependency {
        /**
         * The version of the `bootstrap` package.
         *
         * `SPDX-License-Identifier: Apache-2.0, MIT`
         */
        const val Bootstrap = "5.3.3"

        /**
         * The version of the `bootstrap-icons` package.
         *
         * `SPDX-License-Identifier: MIT`
         */
        const val BootstrapIcons = "1.11.3"

        /**
         * The version of the `org.jetbrains.exposed` package.
         *
         * `SPDX-License-Identifier: BSD-2`
         */
        const val Exposed = "0.48.0"

        /**
         * The version of the `htmx` package.
         *
         * `SPDX-License-Identifier: Apache-2.0`
         */
        const val HTMX = "1.9.10"

        /**
         * The version of the `org.jetbrains.kotlin:kotlin-test-junit` dependency.
         *
         * `SPDX-License-Identifier: Apache-2.0`
         */
        const val KotlinTest = Kotlin

        /**
         * The version of the `com.h2database:h2` package.
         *
         * `SPDX-License-Identifier: BSD-2`
         */
        const val H2JdbcDriver = "2.2.224"

        /**
         * The version of the `ch.qos.logback:logback-classic` dependency.
         *
         * `SPDX-License-Identifier: EPL-1.0`
         */
        const val LogbackClassic = "1.5.6"

        /**
         * The version of the `org.postgresql:postgresql` package.
         *
         * `SPDX-License-Identifier: BSD-2`
         */
        const val PostgresJdbcDriver = "42.7.3"

        /**
         * The version of the `swagger-ui` package.
         *
         * `SPDX-License-Identifier: Apache-2.0`
         */
        const val SwaggerUI = "5.10.3"
    }
}
