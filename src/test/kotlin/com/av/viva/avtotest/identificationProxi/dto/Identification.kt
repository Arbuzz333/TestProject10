package com.av.viva.avtotest.identificationProxi.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class Identification(
    @JsonProperty(value = "business_key", access = JsonProperty.Access.READ_WRITE)
    val businessKey: String,
    @JsonProperty(value = "identification_status", access = JsonProperty.Access.READ_WRITE)
    val status: String,
    @JsonProperty(value = "identification_result", access = JsonProperty.Access.READ_WRITE)
    val result: String
)
