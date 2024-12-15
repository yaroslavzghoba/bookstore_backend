package com.yaroslavzghoba.routing.books

import com.yaroslavzghoba.data.model.BookStorage
import com.yaroslavzghoba.routing.RouteHandlersProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

@Suppress("UnusedReceiverParameter")
fun RouteHandlersProvider.Books.deleteBook(
    bookStorage: BookStorage,
): suspend RoutingContext.() -> Unit = deleteBookHandler@{

    // Return 400 if the `book_id` parameter is not passed or is invalid
    val bookId = call.parameters["book_id"]?.toLongOrNull()
    if (bookId == null) {
        val message = mapOf("message" to "The \"book_id\" parameter is not passed or is invalid")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@deleteBookHandler
    }

    // Return 404 if there is no corresponding book in the storage
    val correspondingBook = bookStorage.getById(id = bookId)
    if (correspondingBook == null) {
        val message = mapOf("message" to "The book with corresponding id not found in the storage")
        call.respond(status = HttpStatusCode.NotFound, message = message)
        return@deleteBookHandler
    }

    bookStorage.deleteById(id = bookId)
    val message = mapOf("message" to "The book was deleted successfully")
    call.respond(status = HttpStatusCode.NoContent, message = message)
}