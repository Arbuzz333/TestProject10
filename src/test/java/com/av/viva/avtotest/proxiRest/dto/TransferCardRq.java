package com.av.viva.avtotest.proxiRest.dto;


public record TransferCardRq(
        String businessKey,
        String transferSum,
        String cardToken,
        String gatewayName,
        String paymentPurpose
) {
}
