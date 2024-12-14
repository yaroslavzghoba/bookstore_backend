package com.yaroslavzghoba.data.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object BooksTable : LongIdTable(name = "Books", columnName = "book_id") {

    val title = varchar(name = "title", length = 255)
    val author = varchar(name = "author", length = 255).nullable()
    val genre = varchar(name = "genre", length = 100).nullable()
    val price = decimal(name = "price", precision = 10, scale = 2)
    val stockQuantity = integer(name = "stock_quantity")
    val supplierId = reference(name = "supplier_id", foreign = SuppliersTable)
}