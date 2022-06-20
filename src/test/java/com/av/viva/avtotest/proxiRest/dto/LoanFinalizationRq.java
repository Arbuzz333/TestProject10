package com.av.viva.avtotest.proxiRest.dto;


public record LoanFinalizationRq(
        String businessKey,
        String agreedLoanTermsId
) {
}
