package com.yaroslavzghoba.routing.suppliers

import com.yaroslavzghoba.data.model.SupplierStorage
import com.yaroslavzghoba.routing.RouteHandlersProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

@Suppress("UnusedReceiverParameter")
fun RouteHandlersProvider.Suppliers.deleteSupplier(
    supplierStorage: SupplierStorage,
): suspend RoutingContext.() -> Unit = deleteBookHandler@{

    // Return 400 if the `supplier_id` parameter is not passed or is invalid
    val supplierId = call.parameters["supplier_id"]?.toLongOrNull()
    if (supplierId == null) {
        val message = mapOf("message" to "The \"supplier_id\" parameter is not passed or is invalid")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@deleteBookHandler
    }

    // Return 404 if there is no corresponding supplier in the storage
    val correspondingBook = supplierStorage.getById(id = supplierId)
    if (correspondingBook == null) {
        val message = mapOf("message" to "The supplier with corresponding id not found in the storage")
        call.respond(status = HttpStatusCode.NotFound, message = message)
        return@deleteBookHandler
    }

    supplierStorage.deleteById(id = supplierId)
    val message = mapOf("message" to "The supplier was deleted successfully")
    call.respond(status = HttpStatusCode.NoContent, message = message)
}