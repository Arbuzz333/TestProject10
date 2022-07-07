package com.av.viva.avtotest.rest.dto


data class MisStartRq(
    val request: RequestStr,
    val hmac: String
) {
    data class RequestStr(
        val process: String
    )

}