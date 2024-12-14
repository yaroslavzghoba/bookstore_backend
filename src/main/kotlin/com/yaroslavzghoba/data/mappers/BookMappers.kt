package com.yaroslavzghoba.data.mappers

import com.yaroslavzghoba.data.dao.BookDao
import com.yaroslavzghoba.model.Book

/**
 * Converts an instance of the [BookDao] class to an instance of the [Book] class.
 */
fun BookDao.toBook() = Book(
    bookId = this.id.value,
    title = this.title,
    author = this.author,
    genre = this.genre,
    price = this.price.toDouble(),
    stockQuantity = this.stockQuantity,
    supplierId = this.supplierId.id.value,
)