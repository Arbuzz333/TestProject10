package com.av.viva.avtotest.rest.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestData(
    val process: String = ""
)
