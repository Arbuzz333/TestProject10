package com.av.viva.avtotest.mobileCamunda.rest

import com.av.viva.avtotest.mobileCamunda.rest.dto.LoanAppStartRequest
import com.av.viva.avtotest.mobileCamunda.rest.dto.LoanPaymentStartRequest
import com.av.viva.avtotest.mobileCamunda.rest.dto.LoanTxStartRequest
import com.av.viva.avtotest.mobileCamunda.rest.dto.RegistrationStartRequest
import com.fasterxml.jackson.databind.JsonNode
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.StreamUtils
import java.nio.charset.Charset
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MobileCamundaTest {

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
    fun startProcessTest(@Autowired webClient: WebTestClient): Unit = runBlocking {
        val rq = RegistrationStartRequest(
            type = "registration-start",
            businessKey = "registration-AAA".replace(baseBQ, UUID.randomUUID().toString()),
            userParams = "{\"messageName\": \"registration-start\",\"client-platform\": \"android\",\"client-version\": \"1701\"}"
        )
        startProcess(webClient, rq)

        delay(500)
        postMessage(webClient, rq.businessKey, "registration-terms-agree")
        postMessage(webClient, rq.businessKey, "phone-approval-started")
        postMessage(webClient, rq.businessKey, "phone-approval-code")
        postMessage(webClient, rq.businessKey, "registration-account-id")
        postMessage(webClient, rq.businessKey, "registration-passport-main")
        postMessage(webClient, rq.businessKey, "registration-passport-olds")
        postMessage(webClient, rq.businessKey, "registration-passport-address")
        postMessage(webClient, rq.businessKey, "registration-video")
        delay(500)
        postMessage(webClient, rq.businessKey, "registration-viva-passport-address-ok")
        postMessage(webClient, rq.businessKey, "registration-viva-passport-main-ok")
        postMessage(webClient, rq.businessKey, "registration-viva-passport-olds-ok")
        postMessage(webClient, rq.businessKey, "registration-viva-video-ok")
        delay(500)
        postMessage(webClient, rq.businessKey, "registration-viva-success")

        val rqLoanappStart = LoanAppStartRequest(
            type = "loanapp-start",
            businessKey = "loanapp-AAA".replace(baseBQ, UUID.randomUUID().toString()),
            params = "{\"messageName\": \"loanapp-start\",\"user-id\": \"879b5423-643d-4002-bb3c-af6ddb630295\"," +
                    "\"client-platform\": \"android\",\"client-version\": \"1701\"}",

        )
        startProcess(webClient, rqLoanappStart)
        delay(500)
        postMessageLoanApp(webClient, rqLoanappStart.businessKey, "loanapp-parameters")
        postMessageLoanApp(webClient, rqLoanappStart.businessKey, "loanapp-family")
        postMessageLoanApp(webClient, rqLoanappStart.businessKey, "loanapp-employment-info")
        postMessageLoanApp(webClient, rqLoanappStart.businessKey, "loanapp-terms-agree")
        postMessageLoanApp(webClient, rqLoanappStart.businessKey, "phone-approval-started-2")
        postMessageLoanApp(webClient, rqLoanappStart.businessKey, "phone-approval-code-2")
        postMessageLoanApp(webClient, rqLoanappStart.businessKey, "loanapp-viva-loan-approved")
        delay(500)
        val rqLoantx = LoanTxStartRequest(
            type = "loantx-start",
            businessKey = "loantx-AAA".replace(baseBQ, UUID.randomUUID().toString()),
            userParams = "{\"messageName\": \"loantx-start\",\"user-id\": \"879b5423-643d-4002-bb3c-af6ddb630295\"," +
                    "\"offer-id\": \"29232\",\"client-platform\": \"android\",\"client-version\": \"1701\"}",
            )
        startProcess(webClient, rqLoantx)
        postMessageLoanTx(webClient, rqLoantx.businessKey, "loantx-loan-parameters")
        delay(500)
        postMessageLoanTx(webClient, rqLoantx.businessKey, "loantx-terms-approved")
        postMessageLoanTx(webClient, rqLoantx.businessKey, "phone-approval-started-tx")
        postMessageLoanTx(webClient, rqLoantx.businessKey, "phone-approval-code-tx")
        delay(500)
        postMessageLoanTx(webClient, rqLoantx.businessKey, "user-card-selected")
        delay(500)
        postMessageLoanTx(webClient, rqLoantx.businessKey, "app-assessment-done")
    }

    @Test
    fun loanPaymentTest(@Autowired webClient: WebTestClient): Unit = runBlocking {
        val rq = LoanPaymentStartRequest(
            type = "loan-payment-start",
            businessKey = "loan-AAA".replace(baseBQ, UUID.randomUUID().toString()),
            userParams = "{\"user-id\":\"6cd933bf-70a8-4085-9db3-2da0e54eafe7\", \"loan-id\":\"3346870\", \"pay-overdue\":\"true\", \"client-platform\":\"android\", \"client-version\":\"1701\"}"
        )
        startProcess(webClient, rq)
        delay(500)
        postMessageLoan(webClient, rq.businessKey, "sum-entered")
        postMessageLoan(webClient, rq.businessKey, "user-card-selected")
        postMessageLoan(webClient, rq.businessKey, "card-transaction-status")
    }

    fun postMessage(webClient: WebTestClient, businessKey: String, method: String) {
        var json = getJsonResource(method)
        json = json.replace("registration-AAA", businessKey, false)
        println("JSON registration $json")

        postWebClient(webClient, json)
    }

    fun postMessageLoanApp(webClient: WebTestClient, businessKey: String, method: String) {
        var json = getJsonResource(method)
        json = json.replace("loanapp-AAA", businessKey, false)
        println("JSON loan app $json")

        postWebClient(webClient, json)
    }

    fun postMessageLoanTx(webClient: WebTestClient, businessKey: String, method: String) {
        var json = getJsonResource(method)
        json = json.replace("loantx-AAA", businessKey, false)
        println("JSON loan tx $json")

        postWebClient(webClient, json)
    }

    fun postMessageLoan(webClient: WebTestClient, businessKey: String, method: String) {
        var json = getJsonResource(method)
        json = json.replace("loan-AAA", businessKey, false)
            .replace("loantx-AAA", businessKey, false)
        println("JSON loan $json")

        postWebClient(webClient, json)
    }

    fun getJsonResource(method: String): String {
        val resource = ClassPathResource("/request/mobileCamunda/$method.json")
        return StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
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