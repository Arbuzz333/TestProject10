package com.av.viva.avtotest.proxiRest.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MessageRq(
    @JsonProperty(value = "message_name", access = JsonProperty.Access.READ_WRITE)
    val messageName: String,
    @JsonProperty(value = "some-key", access = JsonProperty.Access.READ_WRITE)
    val someKey: String?
) {
}