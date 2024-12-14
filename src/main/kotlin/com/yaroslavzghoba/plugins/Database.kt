package com.yaroslavzghoba.plugins

import com.yaroslavzghoba.utils.DbConnectionConfig
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.StdOutSqlLogger

/**
 * Connect to the database.
 *
 * @param dbConnectionConfig Database connection configuration. Includes the url of the database
 * address and the user on whose behalf the database queries will be performed.
 */
@Suppress("UnusedReceiverParameter")
fun Application.configureDatabase(dbConnectionConfig: DbConnectionConfig) {
    Database.connect(
        url = dbConnectionConfig.url,
        user = dbConnectionConfig.user,
        password = dbConnectionConfig.password,
        databaseConfig = DatabaseConfig {
            sqlLogger = StdOutSqlLogger
        },
    )
}