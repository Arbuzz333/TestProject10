package com.av.viva.avtotest.proxiRest.model;

data class CheckAppDataRq(
        val businessKey: String,
        val passPhotoFileId: String,
        val lastname: String,
        val firstname: String,
        val patronimic: String,
        val birthday: String,
        val passSerial: String,
        val passNumber: String
) {
}
