package com.av.viva.avtotest.rest.vivaPay.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class RepayResult(
    @JsonProperty(value =  "ClientUid", access = JsonProperty.Access.READ_WRITE)
    val clientId: String? = "",
    @JsonProperty(value = "PaymentUrl", access = JsonProperty.Access.READ_WRITE)
    val paymentUrl: String,
    @JsonProperty(value = "Transaction", access = JsonProperty.Access.READ_WRITE)
    val transaction: Transaction
) {
    data class Transaction(
        @JsonProperty(value = "Id", access = JsonProperty.Access.READ_WRITE)
        val id: Int = 0,
        @JsonProperty(value = "Uid", access = JsonProperty.Access.READ_WRITE)
        val uuid: String? = "",
        @JsonProperty(value = "Status", access = JsonProperty.Access.READ_WRITE)
        val status: String? = "",
        @JsonProperty(value = "DateTime", access = JsonProperty.Access.READ_WRITE)
        val dateTime: String? = "",
        @JsonProperty(value = "ClientToken", access = JsonProperty.Access.READ_WRITE)
        val clientToken: String? = "",
    )
}
