package com.av.viva.avtotest.rest.vivaPay.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RepayResultRq(
    @JsonProperty(value = "Amount", access = JsonProperty.Access.READ_WRITE)
    val amount: String,
    @JsonProperty(value = "ClientToken", access = JsonProperty.Access.READ_WRITE)
    val clientToken: String,
    @JsonProperty(value = "ClientUid", access = JsonProperty.Access.READ_WRITE)
    val clientUid: String,
    @JsonProperty(value = "TransactionUid", access = JsonProperty.Access.READ_WRITE)
    val transactionUid: String
)
