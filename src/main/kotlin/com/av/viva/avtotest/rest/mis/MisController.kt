package com.av.viva.avtotest.rest.mis

import com.av.viva.avtotest.config.AppTestProperties
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/mis-api/v2",
    consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE])
class MisController(
    val properties: AppTestProperties
) {

    @PostMapping("/get_process_key")
    fun getMonetaCardData(
        jsonNode: String?
    ): String {
        println("get_process_key $jsonNode")

        return "{\"hmac\":\"4140d51dc3f97327e93e3024a113563f\"," +
                "\"response\":{\"status\":\"SUCCESS\",\"message\":\"\",\"process-key\":\"${properties.misProcessKey}\"}}"
    }

    @PostMapping("/activate_demo_account")
    fun activateDemoAccount(
        jsonNode: String?
    ): String {
        println("activate_demo_account $jsonNode")

        return "{\"hmac\":\"6a3843bc2e671549a4febf70c75d0fde\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"}}"
    }

    @PostMapping("/get_registration_type")
    fun getRegistrationType(
        jsonNode: String?
    ): String {
        println("activate_demo_account $jsonNode")

        return "{\"hmac\":\"4e95fec62fdd128e4b12bb61f8f25231\"," +
                "\"response\":{\"status\":\"SUCCESS\",\"message\":\"\",\"result\":\"FULL\"}}"
    }

    @PostMapping("/send_sms")
    fun sendSms(
        jsonNode: String?
    ): String {
        println("send_sms $jsonNode")

        return "{\"hmac\":\"2ddd00b5a8ef9da93243b1a7ed35c1cf\"," +
                "\"response\":{\"message\":\"\",\"status\":\"SUCCESS\",\"0\":\"10387\"}}"
    }

    @PostMapping("/register_lead")
    fun registerLead(
        jsonNode: String?
    ): String {
        println("register_lead $jsonNode")

        return "{\"hmac\":\"6a3843bc2e671549a4febf70c75d0fde\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"}}"
    }

    @PostMapping("/input_passport_main")
    fun inputPassportMain(
        jsonNode: String?
    ): String {
        println("register_lead $jsonNode")

        return "{\"hmac\":\"6a3843bc2e671549a4febf70c75d0fde\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"}}"
    }
}