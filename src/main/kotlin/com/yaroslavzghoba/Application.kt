package com.yaroslavzghoba

import com.yaroslavzghoba.data.BookStorageImpl
import com.yaroslavzghoba.data.SupplierStorageImpl
import com.yaroslavzghoba.plugins.configureDatabase
import com.yaroslavzghoba.plugins.configureRouting
import com.yaroslavzghoba.plugins.configureSerialization
import com.yaroslavzghoba.utils.DbConnectionConfig
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    val dbConnectionConfig = DbConnectionConfig(
        url = environment.config.property("database.url").getString(),
        user = environment.config.property("database.user").getString(),
        password = environment.config.property("database.password").getString(),
    )
    val bookStorage = BookStorageImpl()
    val supplierStorage = SupplierStorageImpl()

    configureDatabase(dbConnectionConfig = dbConnectionConfig)
    configureSerialization()
    configureRouting(
        bookStorage = bookStorage,
        supplierStorage = supplierStorage,
    )
}
