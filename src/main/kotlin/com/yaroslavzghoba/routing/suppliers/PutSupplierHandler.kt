package com.yaroslavzghoba.routing.suppliers

import com.yaroslavzghoba.data.model.SupplierStorage
import com.yaroslavzghoba.mappers.toSupplier
import com.yaroslavzghoba.model.SupplierRequest
import com.yaroslavzghoba.routing.RouteHandlersProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

@Suppress("UnusedReceiverParameter")
fun RouteHandlersProvider.Suppliers.putSupplier(
    supplierStorage: SupplierStorage,
): suspend RoutingContext.() -> Unit = putBookHandler@{

    // Return 400 if the request body cannot be converted to a supplier
    val body = try {
        call.receive<SupplierRequest>()
    } catch (exception: BadRequestException) {
        val message = mapOf("message" to "The request body cannot be converted to a supplier")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@putBookHandler
    }

    // Return 400 if the `supplier_id` parameter is not passed or is invalid
    val supplierId = call.parameters["supplier_id"]?.toLongOrNull()
    if (supplierId == null) {
        val message = mapOf("message" to "The \"supplier_id\" parameter is not passed or is invalid")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@putBookHandler
    }

    // Return 404 if there is no corresponding supplier in the storage
    val correspondingSupplier = supplierStorage.getById(id = supplierId)
    if (correspondingSupplier == null) {
        val message = mapOf("message" to "The book with corresponding id not found in the storage")
        call.respond(status = HttpStatusCode.NotFound, message = message)
        return@putBookHandler
    }

    val updatedSupplier = supplierStorage.update(supplier = body.toSupplier(id = supplierId))
    call.respond(status = HttpStatusCode.OK, message = updatedSupplier)
}