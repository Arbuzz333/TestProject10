package com.av.viva.avtotest.proxiRest.model


data class PayToCardRq(
    val businessKey: String,
    val gatewayName: String,
    val cardToken: String,
    val amount: String,
    val applicationKey: String
    )
