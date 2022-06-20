package com.av.viva.avtotest.proxiRest.dto;


public record UpridRq(
        String businessKey,
        String lastname,
        String firstname,
        String patronimic,
        String birthday,
        String passSerial,
        String passNumber,
        String phoneMobile,
        String snils,
        String inn
) {
}
