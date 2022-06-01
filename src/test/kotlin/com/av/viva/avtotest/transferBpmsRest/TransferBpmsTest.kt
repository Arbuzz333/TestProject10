package com.av.viva.avtotest.transferBpmsRest

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

}