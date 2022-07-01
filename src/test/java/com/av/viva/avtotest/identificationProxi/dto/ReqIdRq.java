package com.av.viva.avtotest.identificationProxi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public record ReqIdRq(
        @JsonProperty(value = "req_id", access = JsonProperty.Access.READ_WRITE)
        String reqId
) {
}
