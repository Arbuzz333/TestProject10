package com.av.viva.avtotest.scoring

import com.av.viva.avtotest.scoring.dto.FactAddress
import com.av.viva.avtotest.scoring.dto.RegAddress
import com.av.viva.avtotest.scoring.dto.ScoringRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import randomDigits
import randomDouble
import randomString


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScoringTest {

    @Test
    fun postWebClient(@Autowired webClient: WebTestClient) {
        (1..50).forEach {
            CoroutineScope(Dispatchers.IO).launch {
                val rq = scoringRequest()
                val response = webClient.post().uri { u ->
                    u.path("/api/scoring")
                        .host("localhost")
                        .port(8080).build()
                }
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(rq)
                    .exchange()
                    .expectStatus()
                    .is2xxSuccessful
                    .expectBody(String::class.java)
                    .returnResult()
                    .responseBody
                println("Count of $it")
            }
        }
    }

    private fun scoringRequest(): ScoringRequest {
        return ScoringRequest(
            randomString(5),
            "http://localhost:8075/service/endpoint/url",
            randomDouble(5),
            randomString(5),
            randomString(5),
            randomString(5),
            randomString(5),
            randomString(5),
            randomString(5),
            randomString(5),
            randomString(5),
            randomString(5),
            randomString(5),
            randomString(5),
            RegAddress(
                randomString(4),
                randomString(4),
                randomString(4),
                randomString(4),
                randomString(4),
                randomString(4),
                randomString(4),
                randomString(4),
                randomString(4)
            ),
            FactAddress(
                randomString(5),
                randomString(5),
                randomString(5),
                randomString(5),
                randomString(5),
                randomString(5),
                randomString(5),
                randomString(5),
                randomString(5)
            ),
            randomString(5),
            randomString(5),
            randomString(5),
            randomDigits(5).toInt(),
            randomDigits(5).toInt(),
            randomDigits(5).toInt(),
            randomDigits(5).toInt(),
            randomDigits(5).toInt(),
            randomDouble(5),
            randomString(5),
            randomString(5),
            randomString(5)
        )
    }

}