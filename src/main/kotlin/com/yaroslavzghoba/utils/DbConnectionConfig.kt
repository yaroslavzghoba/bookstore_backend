package com.yaroslavzghoba.utils

/**
 * Configuration of the application's database connection.
 *
 * @param url The URL address of the database.
 * @param user The name of the user on whose behalf database queries will be performed.
 * @param password The password of the user on whose behalf database queries will be performed.
 */
data class DbConnectionConfig(
    val url: String,
    val user: String,
    val password: String,
)