package com.yaroslavzghoba.routing.books

import com.yaroslavzghoba.data.model.BookStorage
import com.yaroslavzghoba.data.model.SupplierStorage
import com.yaroslavzghoba.mappers.toBook
import com.yaroslavzghoba.model.BookRequest
import com.yaroslavzghoba.routing.RouteHandlersProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

@Suppress("UnusedReceiverParameter")
fun RouteHandlersProvider.Books.putBook(
    bookStorage: BookStorage,
    supplierStorage: SupplierStorage,
): suspend RoutingContext.() -> Unit = putBookHandler@{

    // Return 400 if the request body cannot be converted to a collection
    val body = try {
        call.receive<BookRequest>()
    } catch (exception: BadRequestException) {
        val message = mapOf("message" to "The request body cannot be converted to a book")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@putBookHandler
    }

    // Return 400 if there is no supplier of the book in the storage
    if (body.supplierId == null) {
        val message = mapOf("message" to "Cannot update a book without its supplier")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@putBookHandler
    }

    // Return 404 if there is no supplier of the book in the storage
    val supplier = supplierStorage.getById(id = body.supplierId)
    if (supplier == null) {
        val message = mapOf("message" to "The supplier of the book not found")
        call.respond(status = HttpStatusCode.NotFound, message = message)
        return@putBookHandler
    }

    // Return 400 if the `book_id` parameter is not passed or is invalid
    val bookId = call.parameters["book_id"]?.toLongOrNull()
    if (bookId == null) {
        val message = mapOf("message" to "The \"book_id\" parameter is not passed or is invalid")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@putBookHandler
    }

    // Return 404 if there is no corresponding book in the storage
    val correspondingBook = bookStorage.getById(id = bookId)
    if (correspondingBook == null) {
        val message = mapOf("message" to "The book with corresponding id not found in the storage")
        call.respond(status = HttpStatusCode.NotFound, message = message)
        return@putBookHandler
    }

    val updatedBook = bookStorage.update(book = body.toBook(id = bookId))
    call.respond(status = HttpStatusCode.OK, message = updatedBook)
}