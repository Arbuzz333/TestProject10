package com.av.viva.avtotest.rest.mis.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class BaseResponseDocs(
    val response: RefinDocsResult
) {
    data class RefinDocsResult(
        @JsonProperty(value = "status", access = JsonProperty.Access.READ_WRITE)
        val status: String = "SUCCESS",

        @JsonProperty(value = "individual-conditions", access = JsonProperty.Access.READ_WRITE)
        val individualConditions: String,
        @JsonProperty(value = "payment-schedule", access = JsonProperty.Access.READ_WRITE)
        val paymentSchedule: String,
        @JsonProperty(value = "statement-novation", access = JsonProperty.Access.READ_WRITE)
        val statementNovation: String
    )

}
