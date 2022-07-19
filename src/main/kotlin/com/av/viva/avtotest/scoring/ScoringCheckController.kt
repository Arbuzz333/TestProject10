package com.av.viva.avtotest.scoring

import com.av.viva.avtotest.scoring.dto.CheckReqIdResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/service")
class ScoringCheckController {

    companion object{
        val logger: Logger = LoggerFactory.getLogger(ScoringCheckController::class.java)

        var counter = 0
    }

    @RequestMapping("/check-req-id")
    fun checkReqId(): CheckReqIdResponse {

        return CheckReqIdResponse(result = "SUCCESS")
    }

    @RequestMapping("/req/check-existence")
    fun reqCheckExistence(): String {

        return "SUCCESS"
    }

    @RequestMapping("/req/save")
    fun reqSave(): String {

        return "SUCCESS"
    }

    @RequestMapping("/endpoint/url")
    fun endpointUrl(): String {
        logger.info("endpointUrl ${counter++}")

        return "SUCCESS"
    }
}