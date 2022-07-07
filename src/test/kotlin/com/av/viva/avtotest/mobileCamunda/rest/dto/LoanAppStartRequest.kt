package com.av.viva.avtotest.mobileCamunda.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class LoanAppStartRequest(
    val type: String,
    @JsonProperty(value = "business-key", access = JsonProperty.Access.READ_WRITE)
    val businessKey: String,
    val processName: String = "loan-application",
    val params: String
) {

    data class LoanAppStartParams(
        @JsonProperty("client-platform")
        val clientPlatform: ClientPlatform = ClientPlatform.ANDROID,

        @JsonProperty("client-version")
        val clientVersion: Int = 1320,

        @JsonProperty("user-id")
        val userId: String
    )

    enum class ClientPlatform {
        @JsonProperty(value = "android", access = JsonProperty.Access.READ_WRITE)
        ANDROID,

        @JsonProperty(value = "ios", access = JsonProperty.Access.READ_WRITE)
        IOS;
    }

}
