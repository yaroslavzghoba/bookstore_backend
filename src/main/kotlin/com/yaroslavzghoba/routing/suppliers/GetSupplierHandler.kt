package com.yaroslavzghoba.routing.suppliers

import com.yaroslavzghoba.data.model.SupplierStorage
import com.yaroslavzghoba.routing.RouteHandlersProvider
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext

@Suppress("UnusedReceiverParameter")
fun RouteHandlersProvider.Suppliers.getSuppliers(
    supplierStorage: SupplierStorage,
): suspend RoutingContext.() -> Unit = getSuppliersHandler@{

    call.respond(
        status = HttpStatusCode.OK,
        message = supplierStorage.getAll()
    )
}