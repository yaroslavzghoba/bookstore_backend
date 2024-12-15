package com.yaroslavzghoba.routing.books

import com.yaroslavzghoba.data.model.BookStorage
import com.yaroslavzghoba.routing.RouteHandlersProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

@Suppress("UnusedReceiverParameter")
fun RouteHandlersProvider.Books.getBookById(
    bookStorage: BookStorage,
): suspend RoutingContext.() -> Unit = getBookByIdHandler@{

    // Return 400 if the `book_id` parameter is not passed or is invalid
    val bookId = call.parameters["book_id"]?.toLongOrNull()
    if (bookId == null) {
        val message = mapOf("message" to "The \"book_id\" parameter is not passed or is invalid")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@getBookByIdHandler
    }

    // Return 404 if there is no book with a corresponding id
    val book = bookStorage.getById(id = bookId)
    if (book == null) {
        val message =
            mapOf("message" to "There is no book with \"id\" property equal to \"$bookId\"")
        call.respond(status = HttpStatusCode.NotFound, message = message)
        return@getBookByIdHandler
    }

    call.respond(status = HttpStatusCode.OK, message = book)
}