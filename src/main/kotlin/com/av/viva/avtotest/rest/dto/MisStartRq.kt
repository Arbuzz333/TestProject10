package com.av.viva.avtotest.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class MisStartRq(
    val request: RequestStr,
    val hmac: String
) {
    data class RequestStr(
        val process: String
    )

    data class LoanRq(
        @JsonProperty("uid")
        val uid: String?,
        @JsonProperty("business-key")
        val businessKey: String?,
        @JsonProperty("loan-id")
        val loanId: String?
    )

}