package com.yaroslavzghoba.plugins

import com.yaroslavzghoba.data.model.BookStorage
import com.yaroslavzghoba.data.model.SupplierStorage
import com.yaroslavzghoba.routing.RouteHandlersProvider
import com.yaroslavzghoba.routing.books.deleteBook
import com.yaroslavzghoba.routing.books.getBookById
import com.yaroslavzghoba.routing.books.getBooks
import com.yaroslavzghoba.routing.books.postBook
import com.yaroslavzghoba.routing.books.putBook
import com.yaroslavzghoba.routing.suppliers.deleteSupplier
import com.yaroslavzghoba.routing.suppliers.getSupplierById
import com.yaroslavzghoba.routing.suppliers.getSuppliers
import com.yaroslavzghoba.routing.suppliers.postSupplier
import com.yaroslavzghoba.routing.suppliers.putSupplier
import io.ktor.server.application.Application
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting(
    bookStorage: BookStorage,
    supplierStorage: SupplierStorage,
) {
    routing {
        route(path = "/books") {
            get(
                path = "",
                body = RouteHandlersProvider.Books.getBooks(bookStorage = bookStorage),
            )
            get(
                path = "/{book_id}",
                body = RouteHandlersProvider.Books.getBookById(bookStorage = bookStorage),
            )
            post(
                path = "",
                body = RouteHandlersProvider.Books.postBook(
                    bookStorage = bookStorage,
                    supplierStorage = supplierStorage,
                ),
            )
            put(
                path = "/{book_id}",
                body = RouteHandlersProvider.Books.putBook(
                    bookStorage = bookStorage,
                    supplierStorage = supplierStorage,
                )
            )
            delete(
                path = "/{book_id}",
                body = RouteHandlersProvider.Books.deleteBook(bookStorage = bookStorage)
            )
        }
        route(path = "/suppliers") {
            get(
                path = "",
                body = RouteHandlersProvider.Suppliers
                    .getSuppliers(supplierStorage = supplierStorage),
            )
            get(
                path = "/{supplier_id}",
                body = RouteHandlersProvider.Suppliers
                    .getSupplierById(supplierStorage = supplierStorage),
            )
            post(
                path = "",
                body = RouteHandlersProvider.Suppliers.postSupplier(
                    supplierStorage = supplierStorage,
                ),
            )
            put(
                path = "/{supplier_id}",
                body = RouteHandlersProvider.Suppliers.putSupplier(
                    supplierStorage = supplierStorage,
                ),
            )
            delete(
                path = "/{supplier_id}",
                body = RouteHandlersProvider.Suppliers
                    .deleteSupplier(supplierStorage = supplierStorage),
            )
        }
    }
}