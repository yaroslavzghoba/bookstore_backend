package com.yaroslavzghoba.routing.suppliers

import com.yaroslavzghoba.data.model.SupplierStorage
import com.yaroslavzghoba.routing.RouteHandlersProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

@Suppress("UnusedReceiverParameter")
fun RouteHandlersProvider.Suppliers.getSupplierById(
    supplierStorage: SupplierStorage,
): suspend RoutingContext.() -> Unit = getBookByIdHandler@{

    // Return 400 if the `supplier_id` parameter is not passed or is invalid
    val supplierId = call.parameters["supplier_id"]?.toLongOrNull()
    if (supplierId == null) {
        val message = mapOf("message" to "The \"supplier_id\" parameter is not passed or is invalid")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@getBookByIdHandler
    }

    // Return 404 if there is no book with a corresponding id
    val supplier = supplierStorage.getById(id = supplierId)
    if (supplier == null) {
        val message = mapOf("message" to "There is no supplier with id equal to \"$supplierId\"")
        call.respond(status = HttpStatusCode.NotFound, message = message)
        return@getBookByIdHandler
    }

    call.respond(status = HttpStatusCode.OK, message = supplier)
}