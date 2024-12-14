package com.yaroslavzghoba.data

import com.yaroslavzghoba.data.dao.BookDao
import com.yaroslavzghoba.data.dao.SupplierDao
import com.yaroslavzghoba.data.mappers.toBook
import com.yaroslavzghoba.data.model.BookStorage
import com.yaroslavzghoba.data.tables.BooksTable
import com.yaroslavzghoba.data.tables.SuppliersTable
import com.yaroslavzghoba.model.Book
import com.yaroslavzghoba.utils.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere

class BookStorageImpl : BookStorage {

    override suspend fun getById(id: Long): Book? = suspendTransaction {
        BookDao
            .find { BooksTable.id eq id }
            .map { it.toBook() }
            .firstOrNull()
    }

    override suspend fun getAll(): List<Book> = suspendTransaction {
        BookDao.all().map { it.toBook() }
    }

    override suspend fun insert(book: Book): Book = suspendTransaction {
        // Get the supplier of the book
        val supplier = SupplierDao
            .find { SuppliersTable.id eq book.supplierId }
            .firstOrNull()
            ?: throw NoSuchElementException("Not found a supplier of the book in the storage")

        // Insert a book and return it
        BookDao.new(id = book.bookId) {
            title = book.title
            author = book.author
            genre = book.genre
            price = book.price.toBigDecimal()
            stockQuantity = book.stockQuantity
            supplierId = supplier
        }.toBook()
    }

    override suspend fun update(book: Book): Book = suspendTransaction {
        if (book.bookId == null)
            throw IllegalArgumentException("The id of the book to be updated cannot be null")

        // Get the supplier of the book
        val supplier = SupplierDao
            .find { SuppliersTable.id eq book.supplierId }
            .firstOrNull()
            ?: throw NoSuchElementException("Not found a supplier of the book in the storage")

        // Update a book and return it
        BookDao.findByIdAndUpdate(id = book.bookId) {
            it.title = book.title
            it.author = book.author
            it.genre = book.genre
            it.price = book.price.toBigDecimal()
            it.stockQuantity = book.stockQuantity
            it.supplierId = supplier
        }?.toBook()
            ?: throw NoSuchElementException("Corresponding book is not found in storage")
    }

    override suspend fun deleteAll(): Unit = suspendTransaction {
        BooksTable.deleteAll()
    }

    override suspend fun deleteById(id: Long): Unit = suspendTransaction {
        BooksTable.deleteWhere { BooksTable.id eq id }
    }
}