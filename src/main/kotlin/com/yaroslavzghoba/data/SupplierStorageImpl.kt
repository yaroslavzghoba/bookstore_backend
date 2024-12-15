package com.yaroslavzghoba.data

import com.yaroslavzghoba.data.dao.SupplierDao
import com.yaroslavzghoba.data.mappers.toSupplier
import com.yaroslavzghoba.data.model.SupplierStorage
import com.yaroslavzghoba.data.tables.SuppliersTable
import com.yaroslavzghoba.model.Supplier
import com.yaroslavzghoba.utils.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere

class SupplierStorageImpl : SupplierStorage {

    override suspend fun getById(id: Long): Supplier? = suspendTransaction {
        SupplierDao
            .find { SuppliersTable.id eq id }
            .map { it.toSupplier() }
            .firstOrNull()
    }

    override suspend fun getAll(): List<Supplier> = suspendTransaction {
        SupplierDao.all().map { it.toSupplier() }
    }

    override suspend fun insert(supplier: Supplier): Supplier = suspendTransaction {
        SupplierDao.new(id = supplier.supplierId) {
            supplierName = supplier.supplierName
            contactInfo = supplier.contactInfo
        }.toSupplier()
    }

    override suspend fun update(supplier: Supplier): Supplier = suspendTransaction {
        if (supplier.supplierId == null)
            throw IllegalArgumentException("The id of the book to be updated cannot be null")

        // Update a book and return it
        SupplierDao.findByIdAndUpdate(id = supplier.supplierId) {
            it.supplierName = supplier.supplierName
            it.contactInfo = supplier.contactInfo
        }?.toSupplier()
            ?: throw NoSuchElementException("Corresponding book is not found in storage")
    }

    override suspend fun deleteAll(): Unit = suspendTransaction {
        SuppliersTable.deleteAll()
    }

    override suspend fun deleteById(id: Long): Unit = suspendTransaction {
        SuppliersTable.deleteWhere { SuppliersTable.id eq id }
    }
}