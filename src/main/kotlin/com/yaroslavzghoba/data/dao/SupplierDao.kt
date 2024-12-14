package com.yaroslavzghoba.data.dao

import com.yaroslavzghoba.data.tables.SuppliersTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SupplierDao(id: EntityID<Long>) : LongEntity(id = id) {
    companion object : LongEntityClass<SupplierDao>(SuppliersTable)

    var supplierName by SuppliersTable.supplierName
    var contactInfo by SuppliersTable.contactInfo
}