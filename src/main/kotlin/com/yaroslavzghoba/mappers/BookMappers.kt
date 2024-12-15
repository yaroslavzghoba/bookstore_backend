package com.yaroslavzghoba.mappers

import com.yaroslavzghoba.model.Book
import com.yaroslavzghoba.model.BookRequest

/**
 * Converts an instance of the [BookRequest] class to an instance of the [Book] class.
 */
fun BookRequest.toBook(id: Long?) = Book(
    bookId = id,
    title = this.title,
    author = this.author,
    genre = this.genre,
    price = this.price,
    stockQuantity = this.stockQuantity,
    supplierId = this.supplierId,
)