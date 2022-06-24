package com.av.viva.avtotest.rest.mis

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/mis-api/v2")
class MisController {

    @PostMapping("/get_process_key", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMonetaCardData(
        jsonNode: String?
    ): String {

        println("get_process_key $jsonNode")
        return "{\"hmac\":\"4140d51dc3f97327e93e3024a113563f\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\",\"process-key\":\"Process_Registration_Demo\"}}"
    }

    @PostMapping("/activate_demo_account", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun activateDemoAccount(
        jsonNode: String?
    ): String {

        println("activate_demo_account $jsonNode")
        return "{\"hmac\":\"6a3843bc2e671549a4febf70c75d0fde\",\"response\":{\"status\":\"SUCCESS\",\"message\":\"\"}}"
    }
}