package com.yaroslavzghoba.data.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object SuppliersTable : LongIdTable(name = "Suppliers", columnName = "supplier_id") {

    val supplierName = varchar(name = "supplier_name", length = 255)
    val contactInfo = text(name = "contact_info").nullable()
}