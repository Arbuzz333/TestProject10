package com.av.viva.avtotest.identificationProxi

import com.av.viva.avtotest.config.AppTestProperties
import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.StreamUtils
import java.nio.charset.Charset


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IdentificationControllerTest {

    @Autowired
    lateinit var properties: AppTestProperties

    @Test
    fun identificationProxySuccess(@Autowired webClient: WebTestClient) {
        val sessionId = getSessionBusiness(webClient)
        success(webClient,sessionId ?: "")
    }

    fun success(webClient: WebTestClient, sessionId: String) {
        val resource = ClassPathResource("/request/identificationProxy/passport-photo.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        println("JSON passport photo $json")

        postProxyWebClient(webClient, json, sessionId)

        val selfieResource = ClassPathResource("/request/identificationProxy/selfie-photo.json")
        val jsonSelfie = StreamUtils.copyToString(selfieResource.inputStream, Charset.forName("UTF-8"))
        println("JSON client decline $jsonSelfie")

        postProxyWebClient(webClient, jsonSelfie, sessionId)
    }

    fun passportRepeat(webClient: WebTestClient, bq: String, sessionId: String) {
        val resource = ClassPathResource("/request/identificationProxy/passport-rep-photo.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        println("JSON passport photo $json")

        postProxyWebClient(webClient, json, sessionId)
    }

    fun selfieRepeat(webClient: WebTestClient, bq: String, sessionId: String) {
        val resource = ClassPathResource("/request/identificationProxy/selfie-repeat-photo.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        println("JSON passport photo $json")

        postProxyWebClient(webClient, json, sessionId)
    }

    fun getSessionBusiness(webClient: WebTestClient): String? {
        val sessionId = webClient.get().uri { u ->
            u.path("/api/session").host(properties.identificationProxyHost).port(properties.identificationProxyPort).build()
        }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        println("SessionId: $sessionId")

        val businessKey = webClient.post().uri { u ->
            u.path("/api/process/start").host(properties.identificationProxyHost).port(properties.identificationProxyPort).build()
        }
            .cookie("viva_session_id", sessionId ?: "")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        println("BusinessKey: $businessKey")
        return sessionId
    }

    fun postProxyWebClient(webClient: WebTestClient, json: String, sessionId: String): JsonNode? {
        val response = webClient.post().uri { u ->
            u.path("api/process/message").host(properties.identificationProxyHost).port(properties.identificationProxyPort).build()
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
}