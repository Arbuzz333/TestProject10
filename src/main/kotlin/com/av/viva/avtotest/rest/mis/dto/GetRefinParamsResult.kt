package com.av.viva.avtotest.rest.mis.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate


data class GetRefinParamsResult(
    val status: String = "SUCCESS",

    @JsonProperty(value = "guarantee_payment", access = JsonProperty.Access.READ_WRITE)
    val guaranteeSum: Double,
    @JsonProperty(value = "valid_until_date", access = JsonProperty.Access.READ_WRITE)
    val validUntilDate: LocalDate,
    @JsonProperty(value = "loan-amount", access = JsonProperty.Access.READ_WRITE)
    val loanAmount: Double,
    @JsonProperty(value = "payment", access = JsonProperty.Access.READ_WRITE)
    val payment: Double,
    @JsonProperty(value = "period", access = JsonProperty.Access.READ_WRITE)
    val period: String,
    @JsonProperty(value = "rate", access = JsonProperty.Access.READ_WRITE)
    val rate: Double,
    @JsonProperty(value = "number-payments", access = JsonProperty.Access.READ_WRITE)
    val numberPayments: Int
)
