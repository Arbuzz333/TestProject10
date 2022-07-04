package com.av.viva.avtotest.identificationProxi

import com.av.viva.avtotest.config.AppTestProperties
import com.av.viva.avtotest.entity.BKeySession
import com.av.viva.avtotest.identificationProxi.dto.*
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.data.relational.core.query.Query.query
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.StreamUtils
import randomDigits
import randomString
import java.nio.charset.Charset


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IdentificationControllerTest {

    @Autowired
    lateinit var properties: AppTestProperties

    @Test
    fun startProcessData(@Autowired webClient: WebTestClient) {
        val reqId = randomDigits(7).toString()
        getIdentificationUrl(webClient, reqId)
        getSessionBusiness(webClient, reqId)
    }

    fun getIdentificationUrl(webClient: WebTestClient, reqId: String) {
        val request = ClientDataRq(
            reqId,
            "test/redirect/url",
            randomString(7),
            randomString(7),
            randomString(7),
            "1980-03-15",
            "M",
            "+7" + randomDigits(7),
            randomString(7) + '@' + randomString(3) + ".com",
            randomString(7),
            randomDigits(12).toString(),
            randomString(3),
            randomDigits(4).toString()
        )

        val response = webClient.post().uri { u ->
            u.path("api/identification/url")
                .host(properties.identificationProxyHost)
                .port(properties.identificationProxyPort)
                .build()
        }
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(IdentificationUrlRs::class.java)
            .returnResult()
            .responseBody

        println(response)
        assertTrue(response?.identificationUrl?.endsWith(reqId) ?: false)
    }

    @Test
    fun identificationProxySuccess(@Autowired webClient: WebTestClient) {
        val reqId = randomDigits(7).toString()
        val sessionId = getSessionBusiness(webClient, reqId)
        success(webClient,sessionId ?: "")
    }

    @Test
    fun mediaTest(@Autowired webClient: WebTestClient) {
        val sessionId = getSessionId(webClient)

        val resource = ClassPathResource("/request/identificationProxy/photo.json")
        val request = ObjectMapper().readValue(resource.inputStream, RequestMediaDto::class.java)
        request.fileName = "0_" + randomString(4) + "_0"

        webClient.post().uri { u ->
            u.path("api/photo").host(properties.identificationProxyHost).port(properties.identificationProxyPort).build()
        }
            .contentType(MediaType.APPLICATION_JSON)
            .cookie("viva_session_id", sessionId ?: "")
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
            .cookie("viva_session_id", sessionId ?: "")
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

    fun getSessionId(webClient: WebTestClient): String? {
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
        return sessionId
    }

    fun getSessionBusiness(webClient: WebTestClient, reqId: String): String? {
        val sessionId = getSessionId(webClient)
        val rq = ReqIdRq(reqId)

        val template = getTemplate()
        val bK = template.select(query(where("sessionId").`is`(sessionId ?: "")), BKeySession::class.java).blockFirst()
        val identification = Identification(
            businessKey = bK?.businessKey ?: "",
            status = "ok",
            result = "ok"
        )

        webClient.post().uri { u ->
            u.path("api/identification/save").host(properties.identificationProxyHost).port(properties.identificationProxyPort).build()
        }
            .bodyValue(identification)
            .exchange()
            .expectStatus()
            .is2xxSuccessful

        val businessKey = webClient.post().uri { u ->
            u.path("/api/process/start").host(properties.identificationProxyHost).port(properties.identificationProxyPort).build()
        }
            .cookie("viva_session_id", sessionId ?: "")
            .bodyValue(rq)
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

    fun getTemplate(): R2dbcEntityTemplate {
        val builder: ConnectionFactoryOptions.Builder = ConnectionFactoryOptions.builder()
        builder.option(ConnectionFactoryOptions.HOST, "localhost")
        builder.option(ConnectionFactoryOptions.PORT, 5432)
        builder.option(ConnectionFactoryOptions.USER, "identification_proxy_admin")
        builder.option(ConnectionFactoryOptions.PASSWORD, "identification_proxy_admin")
        builder.option(ConnectionFactoryOptions.DATABASE, "identification_proxy")
        builder.option(ConnectionFactoryOptions.HOST, "localhost")
        builder.option(ConnectionFactoryOptions.DRIVER, "postgresql")

        val connectionFactory: ConnectionFactory = ConnectionFactories.get(builder.build())
        val template = R2dbcEntityTemplate(connectionFactory)
        return template
    }
}