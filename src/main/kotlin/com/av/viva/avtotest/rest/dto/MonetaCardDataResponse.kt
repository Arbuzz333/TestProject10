package com.av.viva.avtotest.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
data class MonetaCardDataResponse(
    @JsonProperty("status")
    private var status: String? = null,
    @JsonProperty("message")
    private val message: String? = null,

    @JsonProperty("moneta_result")
    private val result: String? = null,

    @JsonProperty("card_mask")
    private val cardMask: String? = null,

    @JsonProperty("card_expiration_data")
    private val cardExpirationDate: String? = null
)
