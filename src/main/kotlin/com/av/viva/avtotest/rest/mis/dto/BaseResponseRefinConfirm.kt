package com.av.viva.avtotest.rest.mis.dto


data class BaseResponseRefinConfirm(
    val response: RefinConfirmResult
) {
    data class RefinConfirmResult(
        val status: String = "SUCCESS",

        val result: Result = Result.CONFIRMED
    )

    enum class Result {
        CONFIRMED,
        ERROR
    }

}
