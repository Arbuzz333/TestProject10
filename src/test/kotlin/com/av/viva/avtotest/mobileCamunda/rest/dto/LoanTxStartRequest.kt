package com.av.viva.avtotest.mobileCamunda.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class LoanTxStartRequest(
    val type: String,

    @JsonProperty("business-key")
    val businessKey: String,

    @JsonProperty("params")
    val userParams: String,

    val processName: String = "loan-transaction"
)
