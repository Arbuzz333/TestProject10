package com.av.viva.avtotest.transferBpmsRest

import com.av.viva.avtotest.config.AppTestProperties
import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.StreamUtils
import randomDigits
import java.nio.charset.Charset


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransferBpmsTest {

    @Autowired
    lateinit var properties: AppTestProperties

    companion object{
        const val baseBQ = "viva.startMain0081"
    }

    @Test
    fun processStartMonetaCard(@Autowired webClient: WebTestClient) {
        val bq = baseBQ.replace("0081", randomDigits(5).toString())
        val resource = ClassPathResource("/request/start-transfer.json")
        var json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        json = json.replace(baseBQ, bq, false)
        println("JSON start transfer $json")

        val response = postWebClient(webClient, json)
        assertEquals(response?.get(0)?.get("resultType")?.asText(), "ProcessDefinition")

        val resourceTransferCard = ClassPathResource("/request/transfer-type-card.json")
        var jsonTransferCard = StreamUtils.copyToString(resourceTransferCard.inputStream, Charset.forName("UTF-8"))
        jsonTransferCard = jsonTransferCard.replace(baseBQ, bq, false)
        println("JSON transfer type card $jsonTransferCard")

        val responseTransferCard = postWebClient(webClient, jsonTransferCard)
        assertEquals(responseTransferCard?.get(0)?.get("resultType")?.asText(), "Execution")

        val resourceDataCard = ClassPathResource("/request/moneta-data-card.json")
        var jsonDataCard = StreamUtils.copyToString(resourceDataCard.inputStream, Charset.forName("UTF-8"))
        jsonDataCard = jsonDataCard.replace(baseBQ, bq, false)
        println("JSON moneta data card $jsonDataCard")
        val responseDataCard = postWebClient(webClient, jsonDataCard)
        assertEquals(responseDataCard?.get(0)?.get("resultType")?.asText(), "Execution")
    }

    @Test
    fun processStartTkbCard(@Autowired webClient: WebTestClient) {
        val bq = baseBQ.replace("0081", randomDigits(5).toString())
        val resource = ClassPathResource("/request/start-transfer.json")
        var json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        json = json.replace(baseBQ, bq, false)
        println("JSON start transfer $json")

        val response = postWebClient(webClient, json)
        assertEquals(response?.get(0)?.get("resultType")?.asText(), "ProcessDefinition")

        val resourceTransferCard = ClassPathResource("/request/transfer-type-card.json")
        var jsonTransferCard = StreamUtils.copyToString(resourceTransferCard.inputStream, Charset.forName("UTF-8"))
        jsonTransferCard = jsonTransferCard.replace(baseBQ, bq, false)
        println("JSON transfer type card $jsonTransferCard")

        val responseTransferCard = postWebClient(webClient, jsonTransferCard)
        assertEquals(responseTransferCard?.get(0)?.get("resultType")?.asText(), "Execution")

        val resourceInputCard = ClassPathResource("/request/tkb-input-success-card.json")
        var jsonInputCard = StreamUtils.copyToString(resourceInputCard.inputStream, Charset.forName("UTF-8"))
        jsonInputCard = jsonInputCard.replace(baseBQ, bq, false)
        println("JSON input success card $jsonInputCard")

        val responseInputCard = postWebClient(webClient, jsonInputCard)
        assertEquals(responseInputCard?.get(0)?.get("resultType")?.asText(), "Execution")
    }

    @Test
    fun clientDeclineCard(@Autowired webClient: WebTestClient) {
        val bq = baseBQ.replace("0081", randomDigits(5).toString())
        val resource = ClassPathResource("/request/start-transfer.json")
        var json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        json = json.replace(baseBQ, bq, false)
        println("JSON start transfer $json")

        val response = postWebClient(webClient, json)
        assertEquals(response?.get(0)?.get("resultType")?.asText(), "ProcessDefinition")

        val resourceTransferCard = ClassPathResource("/request/transfer-type-card.json")
        var jsonTransferCard = StreamUtils.copyToString(resourceTransferCard.inputStream, Charset.forName("UTF-8"))
        jsonTransferCard = jsonTransferCard.replace(baseBQ, bq, false)
        println("JSON transfer type card $jsonTransferCard")

        val responseTransferCard = postWebClient(webClient, jsonTransferCard)
        assertEquals(responseTransferCard?.get(0)?.get("resultType")?.asText(), "Execution")

        val resourceInputCard = ClassPathResource("/request/client-decline.json")
        var jsonInputCard = StreamUtils.copyToString(resourceInputCard.inputStream, Charset.forName("UTF-8"))
        jsonInputCard = jsonInputCard.replace(baseBQ, bq, false)
        println("JSON client decline card $jsonInputCard")

        val responseInputCard = postWebClient(webClient, jsonInputCard)
        assertEquals(responseInputCard?.get(0)?.get("resultType")?.asText(), "Execution")
    }

    @Test
    fun processStartSbp(@Autowired webClient: WebTestClient) {
        val bq = baseBQ.replace("0081", randomDigits(5).toString())
        val resource = ClassPathResource("/request/start-transfer.json")
        var json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        json = json.replace(baseBQ, bq, false)
        println("JSON start transfer $json")

        val response = postWebClient(webClient, json)
        assertEquals(response?.get(0)?.get("resultType")?.asText(), "ProcessDefinition")

        val resourceTransferSbp = ClassPathResource("/request/transfer-type-sbp.json")
        var jsonTransferSbp = StreamUtils.copyToString(resourceTransferSbp.inputStream, Charset.forName("UTF-8"))
        jsonTransferSbp = jsonTransferSbp.replace(baseBQ, bq, false)
        println("JSON transfer type $jsonTransferSbp")

        val responseTransferSbp = postWebClient(webClient, jsonTransferSbp)
        assertEquals(responseTransferSbp?.get(0)?.get("resultType")?.asText(), "Execution")

        val resourceInputSbp = ClassPathResource("/request/tkb-input-success-sbp.json")
        var jsonInputSbp = StreamUtils.copyToString(resourceInputSbp.inputStream, Charset.forName("UTF-8"))
        jsonInputSbp = jsonInputSbp.replace(baseBQ, bq, false)
        println("JSON input success $jsonInputSbp")

        val responseInputSbp = postWebClient(webClient, jsonInputSbp)
        assertEquals(responseInputSbp?.get(0)?.get("resultType")?.asText(), "Execution")
    }

    @Test
    fun clientDeclineSbp(@Autowired webClient: WebTestClient) {
        val bq = baseBQ.replace("0081", randomDigits(5).toString())
        val resource = ClassPathResource("/request/start-transfer.json")
        var json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        json = json.replace(baseBQ, bq, false)
        println("JSON start transfer $json")

        val response = postWebClient(webClient, json)
        assertEquals(response?.get(0)?.get("resultType")?.asText(), "ProcessDefinition")

        val resourceTransferSbp = ClassPathResource("/request/transfer-type-sbp.json")
        var jsonTransferSbp = StreamUtils.copyToString(resourceTransferSbp.inputStream, Charset.forName("UTF-8"))
        jsonTransferSbp = jsonTransferSbp.replace(baseBQ, bq, false)
        println("JSON transfer type $jsonTransferSbp")

        val responseTransferSbp = postWebClient(webClient, jsonTransferSbp)
        assertEquals(responseTransferSbp?.get(0)?.get("resultType")?.asText(), "Execution")

        val resourceDecline = ClassPathResource("/request/client-decline.json")
        var jsonDecline = StreamUtils.copyToString(resourceDecline.inputStream, Charset.forName("UTF-8"))
        jsonDecline = jsonDecline.replace(baseBQ, bq, false)
        println("JSON client decline $jsonDecline")

        val responseDecline = postWebClient(webClient, jsonDecline)
        assertEquals(responseDecline?.get(0)?.get("resultType")?.asText(), "Execution")
    }

    fun postWebClient(webClient: WebTestClient, json: String): JsonNode? {
        val response = webClient.post().uri { u ->
            u.path("/engine-rest/message").host("localhost").port(8080).build()
        }
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(json)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        println(response)
        return response
    }

    @Test
    fun transferMonetaProxy(@Autowired webClient: WebTestClient) {
        val (sessionIdTwo, businessKeyTwo) = getSessionBusiness(webClient)
        processProxyMonetaCard(webClient, businessKeyTwo ?: "", sessionIdTwo ?: "")
    }

    @Test
    fun transferTkbProxy(@Autowired webClient: WebTestClient) {
        val (sessionId, businessKey) = getSessionBusiness(webClient)
        processProxyTkbCard(webClient, businessKey ?: "", sessionId ?: "")
    }

    @Test
    fun transferSbpProxy(@Autowired webClient: WebTestClient) {
        val (sessionId, businessKey) = getSessionBusiness(webClient)
        processProxySbp(webClient, businessKey ?: "", sessionId ?: "")
    }

    @Test
    fun transferDeclineCardProxy(@Autowired webClient: WebTestClient) {
        val (sessionId, businessKey) = getSessionBusiness(webClient)
        clientProxyDeclineCard(webClient, businessKey ?: "", sessionId ?: "")
    }

    @Test
    fun transferDeclineSbpProxy(@Autowired webClient: WebTestClient) {
        val (sessionId, businessKey) = getSessionBusiness(webClient)
        clientProxyDeclineSbp(webClient, businessKey ?: "", sessionId ?: "")
    }

    fun getSessionBusiness(webClient: WebTestClient): Pair<String?, String?> {
        val sessionId = webClient.get().uri { u ->
            u.path("/api/session").host("localhost").port(8076).build()
        }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        println("SessionId: $sessionId")

        val businessKey = webClient.post().uri { u ->
            u.path("/api/process/start").host(properties.transferProxyHost).port(properties.transferProxyPort).build()
        }
            .cookie("viva_session_id", sessionId ?: "")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        println("BusinessKey: $businessKey")
        return sessionId to businessKey
    }

    fun postProxyWebClient(webClient: WebTestClient, json: String, sessionId: String): JsonNode? {
        val response = webClient.post().uri { u ->
            u.path("api/process/message").host(properties.transferProxyHost).port(properties.transferProxyPort).build()
        }
            .cookie("viva_session_id", sessionId)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(json)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        println("RESPONSE $response")
        return response
    }

    fun processProxyTkbCard(webClient: WebTestClient, businessKey: String, sessionId: String) {
        val resourceTransferCard = ClassPathResource("/request/proxy/transfer-type-card.json")
        val jsonTransferCard = StreamUtils.copyToString(resourceTransferCard.inputStream, Charset.forName("UTF-8"))
        println("JSON transfer type card $jsonTransferCard")

        postProxyWebClient(webClient, jsonTransferCard, sessionId)

        val resourceInputCard = ClassPathResource("/request/proxy/tkb-input-success-card.json")
        val jsonInputCard = StreamUtils.copyToString(resourceInputCard.inputStream, Charset.forName("UTF-8"))
        println("JSON input success card $jsonInputCard")

        postProxyWebClient(webClient, jsonInputCard, sessionId)
    }

    fun processProxyMonetaCard(webClient: WebTestClient, businessKey: String, sessionId: String = "") {
        val resourceTransferCard = ClassPathResource("/request/proxy/transfer-type-card.json")
        val jsonTransferCard = StreamUtils.copyToString(resourceTransferCard.inputStream, Charset.forName("UTF-8"))
        println("JSON transfer type card $jsonTransferCard")

        postProxyWebClient(webClient, jsonTransferCard, sessionId)

        val resourceInputCard = ClassPathResource("/request/proxy/moneta-data-card.json")
        val jsonInputCard = StreamUtils.copyToString(resourceInputCard.inputStream, Charset.forName("UTF-8"))
        println("JSON input success card $jsonInputCard")

        postProxyWebClient(webClient, jsonInputCard, sessionId)
    }

    fun processProxySbp(webClient: WebTestClient, bq: String, sessionId: String) {
        val resourceTransferSbp = ClassPathResource("/request/proxy/transfer-type-sbp.json")
        val jsonTransferSbp = StreamUtils.copyToString(resourceTransferSbp.inputStream, Charset.forName("UTF-8"))
        println("JSON transfer type $jsonTransferSbp")

        postProxyWebClient(webClient, jsonTransferSbp, sessionId)

        val resourceInputSbp = ClassPathResource("/request/proxy/tkb-input-success-sbp.json")
        val jsonInputSbp = StreamUtils.copyToString(resourceInputSbp.inputStream, Charset.forName("UTF-8"))
        println("JSON input success $jsonInputSbp")

        postProxyWebClient(webClient, jsonInputSbp, sessionId)
    }

    fun clientProxyDeclineCard(webClient: WebTestClient, bq: String, sessionId: String) {
        val resourceTransferCard = ClassPathResource("/request/proxy/transfer-type-card.json")
        val jsonTransferCard = StreamUtils.copyToString(resourceTransferCard.inputStream, Charset.forName("UTF-8"))
        println("JSON transfer type card $jsonTransferCard")

        postProxyWebClient(webClient, jsonTransferCard, sessionId)

        val resourceInputCard = ClassPathResource("/request/proxy/client-decline.json")
        val jsonInputCard = StreamUtils.copyToString(resourceInputCard.inputStream, Charset.forName("UTF-8"))
        println("JSON client decline card $jsonInputCard")

        postProxyWebClient(webClient, jsonInputCard, sessionId)
    }

    fun clientProxyDeclineSbp(webClient: WebTestClient, bq: String, sessionId: String) {
        val resourceTransferSbp = ClassPathResource("/request/proxy/transfer-type-sbp.json")
        val jsonTransferSbp = StreamUtils.copyToString(resourceTransferSbp.inputStream, Charset.forName("UTF-8"))
        println("JSON transfer type $jsonTransferSbp")

        postProxyWebClient(webClient, jsonTransferSbp, sessionId)

        val resourceDecline = ClassPathResource("/request/proxy/client-decline.json")
        val jsonDecline = StreamUtils.copyToString(resourceDecline.inputStream, Charset.forName("UTF-8"))
        println("JSON client decline $jsonDecline")

        postProxyWebClient(webClient, jsonDecline, sessionId)
    }

}