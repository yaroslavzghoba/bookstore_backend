package com.yaroslavzghoba.data.mappers

import com.yaroslavzghoba.data.dao.SupplierDao
import com.yaroslavzghoba.model.Supplier

/**
 * Converts an instance of the [SupplierDao] class to an instance of the [Supplier] class.
 */
fun SupplierDao.toSupplier() = Supplier(
    supplierId = this.id.value,
    supplierName = this.supplierName,
    contactInfo = this.contactInfo,
)