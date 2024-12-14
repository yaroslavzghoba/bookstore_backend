package com.yaroslavzghoba.data.dao

import com.yaroslavzghoba.data.tables.BooksTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BookDao(bookId: EntityID<Long>) : LongEntity(id = bookId) {
    companion object : LongEntityClass<BookDao>(BooksTable)

    var title by BooksTable.title
    var author by BooksTable.author
    var genre by BooksTable.genre
    var price by BooksTable.price
    var stockQuantity by BooksTable.stockQuantity
    var supplierId by SupplierDao referencedOn BooksTable
}