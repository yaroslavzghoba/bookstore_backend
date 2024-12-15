package com.yaroslavzghoba.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The object that represents a passed book in client's request.
 *
 * @param supplierName A name of the supplier.
 * @param contactInfo A contact info of the supplier.
 */
@Serializable
data class SupplierRequest(
    @SerialName("supplier_name") val supplierName: String,
    @SerialName("supplier_info") val contactInfo: String?
)