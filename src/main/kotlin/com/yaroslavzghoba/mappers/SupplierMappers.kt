package com.yaroslavzghoba.mappers

import com.yaroslavzghoba.model.Supplier
import com.yaroslavzghoba.model.SupplierRequest

/**
 * Converts an instance of the [SupplierRequest] class to an instance of the [Supplier] class.
 */
fun SupplierRequest.toSupplier(id: Long?) = Supplier(
    supplierId = id,
    supplierName = this.supplierName,
    contactInfo = this.contactInfo,
)