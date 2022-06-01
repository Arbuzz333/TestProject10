package com.av.viva.avtotest.rest

import com.av.viva.avtotest.config.AppTestProperties
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType.*
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.Charset


@RestController
@RequestMapping("/ois-vivadengi")
class OisVivadengiController(
    val appTestProperties: AppTestProperties
) {

    var vivaService = mapOf("tkb" to "ТКБ", "moneta" to "Монета")

    @PostMapping("/get_moneta_card_data", consumes = [APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_OCTET_STREAM_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun getMonetaCardData(
        jsonNode: Any?
    ): JsonNode? {
        println(jsonNode)
        println("\n")
        val resource = ClassPathResource("/response/get_moneta_card_data.json")
        var json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        json = if (appTestProperties.isMonetaCardData) json else json.replace("SUCCESS", "FAIL")
        println("JSON get_moneta_card_data $json")
        val mapper = ObjectMapper()
        return mapper.readTree(json)
    }

    @PostMapping("/get_sbp_bank_list", consumes = [APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_OCTET_STREAM_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun getSbpBankList(
        body: Any?
    ): JsonNode? {
        println(body)
        val resource = ClassPathResource("/response/get_sbp_bank_list.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        println("JSON get_sbp_bank_list $json")
        val mapper = ObjectMapper()
        return mapper.readTree(resource.inputStream)
    }

    @PostMapping("/get_sbp_possibility_transfer", consumes = [APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_OCTET_STREAM_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun getSbpPossibilityTransfer(
        jsonNode: Any?
    ): JsonNode? {
        println(jsonNode)
        println("\n")
        val resource = if(appTestProperties.isSbpPossibilityTransfer) ClassPathResource("/response/status-success.json")
        else ClassPathResource("/response/status-fail.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        println("JSON get_sbp_possibility_transfer $json")
        val mapper = ObjectMapper()
        return mapper.readTree(resource.inputStream)
    }

    @PostMapping("/get_gateway_for_transfer", consumes = [APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_OCTET_STREAM_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun getGatewayForTransfer(
        jsonNode: Any?
    ): JsonNode? {
        println(jsonNode)
        println("\n")
        val resource = ClassPathResource("/response/get_gateway_for_transfer.json")
        var json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        json = json.replace("XXX", vivaService[appTestProperties.gatewayName] ?: "???")
        println("JSON get_gateway_for_transfer $json")
        val mapper = ObjectMapper()
        return mapper.readTree(json)
    }

    @PostMapping("/get_tkb_card_data", consumes = [APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_OCTET_STREAM_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun getTkbCardData(
        jsonNode: Any?
    ): JsonNode? {
        println(jsonNode)
        println("\n")
        val resource = if (appTestProperties.isTkbCardData) ClassPathResource("/response/get_tkb_card_data.json")
        else ClassPathResource("/response/status-fail.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        println("JSON get_tkb_card_data $json")
        val mapper = ObjectMapper()
        return mapper.readTree(resource.inputStream)
    }

    @PostMapping("/get_tkb_card_input_result", consumes = [APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_OCTET_STREAM_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun getTkbCardInputResult(
        jsonNode: Any?
    ): JsonNode? {
        println(jsonNode)
        println("\n")
        val resource = ClassPathResource("/response/get_tkb_card_input_result.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        println("JSON get_tkb_card_input_result $json")
        val mapper = ObjectMapper()
        return mapper.readTree(resource.inputStream)
    }

    @PostMapping("/get_tkb_url", consumes = [APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_OCTET_STREAM_VALUE], produces = [APPLICATION_JSON_VALUE])
    fun getTkbUrl(
        jsonNode: Any?
    ): JsonNode? {
        println(jsonNode)
        println("\n")
        val resource = ClassPathResource("/response/get_tkb_url.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        println("JSON get_tkb_url $json")
        val mapper = ObjectMapper()
        return mapper.readTree(resource.inputStream)
    }
}