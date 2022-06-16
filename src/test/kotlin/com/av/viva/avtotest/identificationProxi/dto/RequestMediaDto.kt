package com.av.viva.avtotest.identificationProxi.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class RequestMediaDto(
    @JsonProperty(value = "bytes_array", access = JsonProperty.Access.READ_WRITE)
    val bytes: String,
    @JsonProperty(value = "file_name", access = JsonProperty.Access.READ_WRITE)
    var fileName: String,
    @JsonProperty(value = "file_path", access = JsonProperty.Access.READ_WRITE)
    val filePath: String
)
