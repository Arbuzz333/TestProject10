package com.av.viva.avtotest.mobileCamunda.rest

import com.av.viva.avtotest.mobileCamunda.rest.dto.LoanAppStartRequest
import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.StreamUtils
import randomDigits
import java.nio.charset.Charset
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MobileDemoCamundaTest {

    companion object{
        const val baseBQ = "AAA"
    }

    fun postWebClient(webClient: WebTestClient, json: String): JsonNode? {
        val response = webClient.post().uri { u ->
            u.path("/engine-rest/message").host("localhost").port(8090).build()
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
    fun startProcessTest(@Autowired webClient: WebTestClient) {
        val rq = LoanAppStartRequest(
            type = "loanapp-start",
            businessKey = "registration-AAA".replace(baseBQ, UUID.randomUUID().toString()),
            params = "{\"user-id\": \"b659ed0e-77df-4083-9338-ca5222bc7e6d\",\"client-platform\": \"android\",\"client-version\": \"1701000\"}"
        )
        startProcess(webClient, rq)

        postMessage(webClient, rq.businessKey, "registration-terms-agree")
        postMessage(webClient, rq.businessKey, "phone-approval-started")
        postMessage(webClient, rq.businessKey, "phone-approval-code")
        postMessage(webClient, rq.businessKey, "registration-account-id")
        postMessage(webClient, rq.businessKey, "registration-passport-main")
        postMessage(webClient, rq.businessKey, "registration-passport-olds")
        postMessage(webClient, rq.businessKey, "registration-passport-address")
        postMessage(webClient, rq.businessKey, "registration-video")
    }

    fun postMessage(webClient: WebTestClient, businessKey: String, method: String) {
        val resource = ClassPathResource("/request/mobileCamunda/$method.json")
        var json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        json = json.replace("registration-AAA", businessKey, false)
        println("JSON registration start $json")

        postWebClient(webClient, json)
    }

    fun startProcess(webClient: WebTestClient, rq: Any): String? {
        println(rq)
        val response = webClient.post().uri { u ->
            u.path("/start-process")
                .host("localhost")
                .port(8090).build()
        }
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(rq)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        println(response)
        return response
    }

}