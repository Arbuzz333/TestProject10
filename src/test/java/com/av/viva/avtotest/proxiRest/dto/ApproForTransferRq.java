package com.av.viva.avtotest.proxiRest.dto;

public record ApproForTransferRq(
        String businessKey,
        String transferSum,
        String lastname,
        String firstname,
        String patronimic,
        String birthday,
        String gender,
        String phoneMobile,
        String email,
        String snils,
        String inn,
        String passSerial,
        String passNumber,
        String passIssuerCode
) {
}
