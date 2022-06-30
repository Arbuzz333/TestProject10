package com.av.viva.avtotest.identificationProxi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record ClientDataRq(
        @JsonProperty(value = "req_id", access = JsonProperty.Access.READ_WRITE)
        String reqId,
        @JsonProperty(value = "redirect_url", access = JsonProperty.Access.READ_WRITE)
        String redirectUrl,
        String lastname,
        String firstname,
        String patronimic,
        String birthday,
        String gender, //M, F
        @JsonProperty(value = "phone_mobile", access = JsonProperty.Access.READ_WRITE)
        String phoneMobile,
        String email,
        String snils,
        String inn,
        @JsonProperty(value = "pass_serial", access = JsonProperty.Access.READ_WRITE)
        String passSerial,
        @JsonProperty(value = "pass_number", access = JsonProperty.Access.READ_WRITE)
        String passNumber
) {
}
