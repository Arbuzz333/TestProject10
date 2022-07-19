package com.av.viva.avtotest.scoring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record FactAddress(
        @JsonProperty("fact_post")
        String factPost,
        @JsonProperty("fact_region")
        String factRegion,
        @JsonProperty("fact_city")
        String factCity,
        @JsonProperty("fact_settlement")
        String factSettlement,
        @JsonProperty("fact_street")
        String factStreet,
        @JsonProperty("fact_house")
        String factHouse,
        @JsonProperty("fact_housing")
        String factHousing,
        @JsonProperty("fact_constructing")
        String factConstructing,
        @JsonProperty("fact_flat")
        String factFlat
) {

}
