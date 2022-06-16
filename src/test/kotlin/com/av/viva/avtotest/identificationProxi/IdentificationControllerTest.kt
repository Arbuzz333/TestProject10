package com.av.viva.avtotest.identificationProxi

import com.av.viva.avtotest.config.AppTestProperties
import com.av.viva.avtotest.identificationProxi.dto.RequestMediaDto
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.StreamUtils
import randomString
import java.nio.charset.Charset
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IdentificationControllerTest {

    @Autowired
    lateinit var properties: AppTestProperties

    @Test
    fun identificationProxySuccess(@Autowired webClient: WebTestClient) {
        val sessionId = getSessionBusiness(webClient)
        success(webClient,sessionId ?: "")
    }

    @Test
    fun mediaTest(@Autowired webClient: WebTestClient) {
        val resource = ClassPathResource("/request/identificationProxy/photo.json")
        val request = ObjectMapper().readValue(resource.inputStream, RequestMediaDto::class.java)
        request.fileName = "0_" + randomString(4) + "_0"

        webClient.post().uri { u ->
            u.path("api/photo").host(properties.identificationProxyHost).port(properties.identificationProxyPort).build()
        }
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .is2xxSuccessful

        val resourceVideo = ClassPathResource("/request/identificationProxy/video.json")
        val requestVideo = ObjectMapper().readValue(resourceVideo.inputStream, RequestMediaDto::class.java)
        requestVideo.fileName = "0_" + randomString(4) + "_0"

        webClient.post().uri { u ->
            u.path("api/video").host(properties.identificationProxyHost).port(properties.identificationProxyPort).build()
        }
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestVideo)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
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