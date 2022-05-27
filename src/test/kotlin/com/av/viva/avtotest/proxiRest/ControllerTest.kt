package com.av.viva.avtotest.proxiRest

import com.av.viva.avtotest.proxiRest.model.MessageRq
import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.Duration


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    @Test
    fun processMessage(@Autowired webClient: WebTestClient) {
        val rqOne = MessageRq(messageName = "income-data", someKey = null)
        val rqTwo = MessageRq(messageName = "income-data", someKey = "Some value Done")
        val listRq = listOf(rqOne, rqTwo)

        listRq.forEach { rq ->
            webClient.post().uri { u ->
                u.path("/api/process/message").host("localhost").port(8080).build()
            }
                .bodyValue(rq)
                .cookie("viva_session_Id", "1110555")
                .exchange()
                .expectStatus()
                .is2xxSuccessful
        }
    }

    @Test
    fun getRegions(@Autowired webClient: WebTestClient) {
        val regions = webClient.get()
            .uri { u ->
                u.path("/api/address/regions")
                    .queryParam("part_string", "Вол")
                    .host("localhost").port(8080).build()
            }
            .cookie("viva_session_Id", "1110555")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(List::class.java)
            .returnResult()
            .responseBody

        val reg = regions as List<LinkedHashMap<String, String>>
        assertEquals(reg.filter { it["region_name"] == "Вологодская" }.size, 1)

        println(regions)
    }

    @Test
    fun getCitiesByPart(@Autowired webClient: WebTestClient) {
        val regions = webClient.get()
            .uri { u ->
                u.path("/api/address/cities")
                    .queryParam("part_string", "Волочек")
                    .queryParam("parent_key", "6000900000800")
                    .host("localhost").port(8080).build()
            }
            .cookie("viva_session_Id", "1110555")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(List::class.java)
            .returnResult()
            .responseBody

        val reg = regions as List<LinkedHashMap<String, String>>
        assertEquals(reg.filter { it["city_name"] == "Волочек" }.size, 1)

        println(regions)
    }

    @Test
    fun getSettlementsByPart(@Autowired webClient: WebTestClient) {
        val regions = webClient.get()
            .uri { u ->
                u.path("/api/address/settlements")
                    .queryParam("part_string", "А")
                    .queryParam("parent_key", "6000900000800")
                    .host("localhost").port(8080).build()
            }
            .cookie("viva_session_Id", "1110555")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(List::class.java)
            .returnResult()
            .responseBody

        val reg = regions as List<LinkedHashMap<String, String>>
        assertEquals(reg.filter { it["settlement_name"] == "Асаново" }.size, 1)

        println(regions)
    }

    @Test
    fun getStreetsByPart(@Autowired webClient: WebTestClient) {
        val regions = webClient.get()
            .uri { u ->
                u.path("/api/address/streets")
                    .queryParam("part_string", "Мо")
                    .queryParam("parent_key", "6000000100000")
                    .host("localhost").port(8080).build()
            }
            .cookie("viva_session_Id", "1110555")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(List::class.java)
            .returnResult()
            .responseBody

        val reg = regions as List<LinkedHashMap<String, String>>
        assertEquals(reg.filter { it["street_name"] == "Московская" }.size, 1)

        println(regions)
    }

    @Test
    fun getTkbCardData(@Autowired webClient: WebTestClient) {
        val cards = webClient.get()
            .uri { u ->
                u.path("/api/address/tkb-card-token")
                    .queryParam("tkb_card_token", "5599002012290001")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(List::class.java)
            .returnResult()
            .responseBody

//        val reg = cards as List<LinkedHashMap<String, String>>
//        assertNotNull(reg.filter { it["region_name"] == "Вологодская" })

        println(cards)
    }

    @Test
    fun getTkbUrl(@Autowired webClient: WebTestClient) {
        val cards = webClient.get()
            .uri { u ->
                u.path("/api/address/tkb-url")
                    .queryParam("gateway_form_url", "online.vivadengi.ru")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        assertNotNull(cards?.get("tkb_req_id"))//Bind_lite_DEV_163

        println(cards)
    }

    @Test
    fun getTkbCardInputResult(@Autowired webClient: WebTestClient) {
        val cards = webClient.get()
            .uri { u ->
                u.path("/api/address/get-tkb-card-input-result")
                    .queryParam("tkb_req_id", "Bind_lite_DEV_163")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        assertNotNull(cards?.get("tkb_status"))

        println(cards)
    }

    @Test
    fun sendSms(@Autowired webClient: WebTestClient) {
        val cards = webClient.get()
            .uri { u ->
                u.path("/api/address/send-sms")
                    .queryParam("phone", "71234567890")
                    .queryParam("message", "TEXT")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        assertEquals(cards?.get("status")?.asText(), "SUCCESS")

        println(cards)
    }

    @Test
    fun getSbpPossibilityTransfer(@Autowired webClient: WebTestClient) {
        val cards = webClient.get()
            .uri { u ->
                u.path("/api/address/sbp_possibility_transfer")
                    .queryParam("amount", "100")
                    .queryParam("phone_mobile", "+79111111111")
                    .queryParam("sbp_bank_key", "100000000061")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        assertEquals(cards?.get("status")?.asText(), "SUCCESS")

        println(cards)
    }

    @Test
    fun getPartnerUrl(@Autowired webClient: WebTestClient) {
        val cards = webClient.get()
            .uri { u ->
                u.path("/api/address/get-partner-url")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        assertNotNull(cards?.get("partner_url"))

        println(cards)
    }

    @Test
    fun getMonetaCardData(@Autowired webClient: WebTestClient) {
        val webTestClient = webClient.mutate()
            .responseTimeout(Duration.ofMillis(7000))
            .build()
        val cards = webTestClient.get()
            .uri { u ->
                u.path("/api/address/get-moneta-card-data")
                    .queryParam("amount", "10000")
                    .queryParam("moneta_card_token", "111222223333")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        assertNotNull(cards?.get("moneta_result"))

        println(cards)
    }

    @Test
    fun getSbpBankList(@Autowired webClient: WebTestClient) {
        val webTestClient = webClient.mutate()
            .responseTimeout(Duration.ofMillis(7000))
            .build()
        val cards = webTestClient.get()
            .uri { u ->
                u.path("/api/address/get-sbp-bank-list")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        assertNotNull(cards?.get("banks"))

        println(cards)
    }
}
