package com.av.viva.avtotest.scoring.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
data class CheckReqIdResponse (
    @JsonProperty("result")
    val result: String

)
