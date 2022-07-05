package com.av.viva.avtotest.mobileCamunda.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class RegistrationStartRequest(
    val type: String,
    @JsonProperty(value = "business-key", access = JsonProperty.Access.READ_WRITE)
    val businessKey: String,
    @JsonProperty("params")
    val userParams: String
)
