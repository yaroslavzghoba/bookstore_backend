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
fun RouteHandlersProvider.Books.postBook(
    bookStorage: BookStorage,
    supplierStorage: SupplierStorage,
): suspend RoutingContext.() -> Unit = postBookHandler@{

    // Return 400 if the request body cannot be converted to a collection
    val body = try {
        call.receive<BookRequest>()
    } catch (exception: BadRequestException) {
        val message = mapOf("message" to "The request body cannot be converted to a book")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@postBookHandler
    }

    // Return 400 if there is no supplier of the book in the storage
    if (body.supplierId == null) {
        val message = mapOf("message" to "Cannot insert a book without its supplier")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@postBookHandler
    }

    // Return 404 if there is no supplier of the book in the storage
    val supplier = supplierStorage.getById(id = body.supplierId)
    if (supplier == null) {
        val message = mapOf("message" to "The supplier of the book not found")
        call.respond(status = HttpStatusCode.NotFound, message = message)
        return@postBookHandler
    }

    val insertedBook = bookStorage.insert(book = body.toBook(id = null))
    call.respond(status = HttpStatusCode.OK, message = insertedBook)
}