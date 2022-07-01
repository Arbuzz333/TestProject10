package com.av.viva.avtotest.identificationProxi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record IdentificationUrlRs(
        @JsonProperty(value = "identification_url", access = JsonProperty.Access.READ_WRITE)
        String identificationUrl
) {
}
