package com.yaroslavzghoba.routing.books

import com.yaroslavzghoba.data.model.BookStorage
import com.yaroslavzghoba.routing.RouteHandlersProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

@Suppress("UnusedReceiverParameter")
fun RouteHandlersProvider.Books.getBooks(
    bookStorage: BookStorage,
): suspend RoutingContext.() -> Unit = getBooksHandler@{

    call.respond(
        status = HttpStatusCode.OK,
        message = bookStorage.getAll()
    )
}