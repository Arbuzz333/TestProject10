package com.av.viva.avtotest.proxiRest.dto;


public record TransferSbpRq(
        String businessKey,
        String transferSum,
        String sbpBankKey,
        String phoneMobile,
        String paymentPurpose
) {
}
