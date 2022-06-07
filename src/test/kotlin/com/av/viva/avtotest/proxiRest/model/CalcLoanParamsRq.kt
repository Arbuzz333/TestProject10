package com.av.viva.avtotest.proxiRest.model;

data class CalcLoanParamsRq(
        val businessKey: String,
        val loanSum: Int,
        val insurance: String
) {
}
