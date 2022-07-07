package com.av.viva.avtotest.rest.dto

data class NotificationRq(
    val uid: String,
    val  data: String,
    val hmac: String
)
