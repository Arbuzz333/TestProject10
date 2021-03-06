package com.av.viva.avtotest.proxiRest

import com.av.viva.avtotest.proxiRest.dto.*
import com.av.viva.avtotest.proxiRest.dto.LoanFinalizationRq
import com.av.viva.avtotest.proxiRest.model.*
import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.StreamUtils
import java.nio.charset.Charset
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

    @Test
    fun getLoanTarget(@Autowired webClient: WebTestClient) {
        val cards = webClient.get()
            .uri { u ->
                u.path("/api/address/get-loan-target")
                    .host("localhost").port(8080).build()
            }
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody

        assertNotNull(cards?.get("items"))

        println(cards)
    }

    fun postWebClient(webClient: WebTestClient, json: Any, method: String): JsonNode? {
        return webClient.post()
            .uri { u ->
                u.path("/api/address/$method")
                    .host("localhost").port(8080).build()
            }
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(json)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody(JsonNode::class.java)
            .returnResult()
            .responseBody
    }

    @Test
    fun loanAppReview(@Autowired webClient: WebTestClient) {
        val resource = ClassPathResource("/request/loanapp-review.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        val cards = postWebClient(webClient, json, "loanapp-review")

        assertEquals(cards?.get("status")?.asText(), "SUCCESS")
        println(cards)
    }

    @Test
    fun calcLoanParams(@Autowired webClient: WebTestClient) {
        val rq = CalcLoanParamsRq("abcdef-ghijklmop-qrstuv-wxyz", 1000, "true")
        val response = postWebClient(webClient, rq, "calc_loan_params")

        assertTrue(response?.get("loan_terms")?.isArray ?: false)
        println(response)
    }

    @Test
    fun getApplicationLimit(@Autowired webClient: WebTestClient) {
        val rq = ApplicationLimitRq("abcdef-ghijklmop-qrstuv-wxyz","111224566")
        val response = postWebClient(webClient, rq, "get_application_limit")

        assertNotNull(response?.get("limit"))
        println(response)
    }

    @Test
    fun getPassportIssuer(@Autowired webClient: WebTestClient) {
        val rq = PassportIssuerRq("abcdef-ghijklmop-qrstuv-wxyz","111224566")
        val response = postWebClient(webClient, rq, "get_passport_issuer")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun regFactSigningConditions(@Autowired webClient: WebTestClient) {
        val rq = RegFactSigningConditionsRq("abcdef-ghijklmop-qrstuv-wxyz",
            "123456", "+79111111111", "111224566")
        val response = postWebClient(webClient, rq, "reg_fact_signing_conditions")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun payToCard(@Autowired webClient: WebTestClient) {
        val rq = PayToCardRq("abcdef-ghijklmop-qrstuv-wxyz",
            "ТКБ", "12345645665", "34000.00", "111224566")
        val response = postWebClient(webClient, rq, "pay_to_card")

//        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun loanFinalization(@Autowired webClient: WebTestClient) {
        val rq = LoanFinalizationRq("abcdef-ghijklmop-qrstuv-wxyz","111224566")
        val response = postWebClient(webClient, rq, "loan_finalization")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun loanDocs(@Autowired webClient: WebTestClient) {
        val rq = LoanDocsRq("abcdef-ghijklmop-qrstuv-wxyz","111224566")
        val response = postWebClient(webClient, rq, "loan_docs")

//        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun getFile(@Autowired webClient: WebTestClient) {
        val rq = FileRq("abcdef-ghijklmop-qrstuv-wxyz","/asuz_documents/m_app/2020/10/07/38348a2d49ea18ea01c7d6c787bd5901_1602071328.png")
        val response = postWebClient(webClient, rq, "get_file")

        assertNotNull(response?.get("file"))
        println(response)
    }

    @Test
    fun regEvent(@Autowired webClient: WebTestClient) {
        val rq = RegEventRq("abcdef-ghijklmop-qrstuv-wxyz",
            "ATTEMPT_TO_CRACK_SMS_CODE",
            "Иванов Иван Иванович, 14.11.1985, 5601 123456, +79999999999")
        val response = postWebClient(webClient, rq, "reg_event")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun checkAppData(@Autowired webClient: WebTestClient) {
        val rq = CheckAppDataRq("abcdef-ghijklmop-qrstuv-wxyz",
            "123456","Иванов","Иван","Иванович",
            "10.10.1985","0000","000000"
        )
        val response = postWebClient(webClient, rq, "check_app_data")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun checkPassPhoto(@Autowired webClient: WebTestClient) {
        val rq = CheckPassPhotoRq("abcdef-ghijklmop-qrstuv-wxyz",
            "\\/asuz_documents\\/m_app\\/2020\\/10\\/07\\/38348a2d49ea18ea01c7d6c787bd5901_1602071328.png"
        )
        val response = postWebClient(webClient, rq, "check_pass_photo")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun checkPassSelfy(@Autowired webClient: WebTestClient) {
        val rq = CheckPassSelfyRq("abcdef-ghijklmop-qrstuv-wxyz",
            "123456","7891011"
        )
        val response = postWebClient(webClient, rq, "check_pass_selfy")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun checkSelfy(@Autowired webClient: WebTestClient) {
        val rq = CheckSelfyRq("abcdef-ghijklmop-qrstuv-wxyz",
            "\\/asuz_documents\\/m_app\\/2020\\/10\\/07\\/38348a2d49ea18ea01c7d6c787bd5901_1602071328.png"
        )
        val response = postWebClient(webClient, rq, "check_selfy")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun getFileHash(@Autowired webClient: WebTestClient) {
        val rq = FileHashRq("abcdef-ghijklmop-qrstuv-wxyz",
            "20220522_437175ba4191210ee004e1d937494d09"
        )
        val response = postWebClient(webClient, rq, "get_file_hash")

//        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun uprid(@Autowired webClient: WebTestClient) {
        val rq = UpridRq("abcdef-ghijklmop-qrstuv-wxyz",
            "ИВАНОВ","ИВАН","ИВАНОВИЧ","10.10.1998","0000","000000",
            "+79111111111","111-111-111 11","11111111111"
        )
        val response = postWebClient(webClient, rq, "uprid")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun uploadFile(@Autowired webClient: WebTestClient) {
        val resource = ClassPathResource("/request/proxy/upload-file.json")
        val json = StreamUtils.copyToString(resource.inputStream, Charset.forName("UTF-8"))
        val response = postWebClient(webClient, json, "upload_file")

        assertEquals(response?.get("status")?.asText(), "SUCCESS")
        println(response)
    }

    @Test
    fun transferSbp(@Autowired webClient: WebTestClient) {
        val resource = TransferSbpRq("abcdef-ghijklmop-qrstuv-wxyz",
            "10000","100000000061","+79111111111","")
        val response = postWebClient(webClient, resource, "transfer_sbp")

        assertNotNull(response?.get("transfer_id"))
        println(response)
    }

    @Test
    fun transferCard(@Autowired webClient: WebTestClient) {
        val resource = TransferCardRq("abcdef-ghijklmop-qrstuv-wxyz",
            "10000","100000000061565","ТКБ","")
        val response = postWebClient(webClient, resource, "transfer_card")

        assertNotNull(response?.get("transfer_id"))
        println(response)
    }

    @Test
    fun getTransferStatus(@Autowired webClient: WebTestClient) {
        val resource = TransferStatusRq("abcdef-ghijklmop-qrstuv-wxyz",
            "12345679")
        val response = postWebClient(webClient, resource, "get_transfer_status")

        assertNotNull(response?.get("transfer_status"))
        println(response)
    }

    @Test
    fun getApproForTransfer(@Autowired webClient: WebTestClient) {
        val resource = ApproForTransferRq("abcdef-ghijklmop-qrstuv-wxyz",
            "15000.00","Иванов","Иван","Иванович","10.10.1985","M",
            "+79111111111","ivanov@mail.ru","111-111-111 11","123456789123","0000",
            "000000","111-111")
        val response = postWebClient(webClient, resource, "get_appro_for_transfer")

        assertEquals(response?.get("result")?.asText(), "APPROVED")
        println(response)
    }
}
