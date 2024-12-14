package com.yaroslavzghoba.model

import kotlinx.serialization.SerialName

/**
 * The object that represents a book.
 *
 * @param bookId Unique book identifier.
 * @param title A title of the book.
 * @param author An author of the book.
 * @param genre A genre of the book.
 * @param price A price of the book.
 * @param stockQuantity The quantity of these books in the stock.
 * @param supplierId An unique id of the books' supplier.
 */
data class Book(
    @SerialName("book_id") val bookId: Long?,
    @SerialName("title") val title: String,
    @SerialName("author") val author: String?,
    @SerialName("genre") val genre: String?,
    @SerialName("price") val price: Double,
    @SerialName("stock_quantity") val stockQuantity: Int,
    @SerialName("supplier_id") val supplierId: Long?,
)