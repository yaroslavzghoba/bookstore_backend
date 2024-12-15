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
fun RouteHandlersProvider.Suppliers.postSupplier(
    supplierStorage: SupplierStorage,
): suspend RoutingContext.() -> Unit = postBookHandler@{

    // Return 400 if the request body cannot be converted to a supplier
    val body = try {
        call.receive<SupplierRequest>()
    } catch (exception: BadRequestException) {
        val message = mapOf("message" to "The request body cannot be converted to a supplier")
        call.respond(status = HttpStatusCode.BadRequest, message = message)
        return@postBookHandler
    }

    val insertedSupplier = supplierStorage.insert(supplier = body.toSupplier(id = null))
    call.respond(status = HttpStatusCode.OK, message = insertedSupplier)
}