package com.av.viva.avtotest.scoring.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record RegAddress(
        @JsonProperty(value = "reg_post", access = JsonProperty.Access.READ_WRITE)
        String regPost,
        @JsonProperty(value = "reg_region", access = JsonProperty.Access.READ_WRITE)
        String regRegion,
        @JsonProperty(value = "reg_city", access = JsonProperty.Access.READ_WRITE)
        String regCity,
        @JsonProperty(value = "reg_settlement", access = JsonProperty.Access.READ_WRITE)
        String regSettlement,
        @JsonProperty(value = "reg_street", access = JsonProperty.Access.READ_WRITE)
        String regStreet,
        @JsonProperty(value = "reg_house", access = JsonProperty.Access.READ_WRITE)
        String regHouse,
        @JsonProperty(value = "reg_housing", access = JsonProperty.Access.READ_WRITE)
        String regHousing,
        @JsonProperty(value = "reg_constructing", access = JsonProperty.Access.READ_WRITE)
        String regConstructing,
        @JsonProperty(value = "reg_flat", access = JsonProperty.Access.READ_WRITE)
        String regFlat
) {
}
