package com.av.viva.avtotest.rest.vivaPay

import com.av.viva.avtotest.rest.vivaPay.dto.RepayResult
import com.av.viva.avtotest.rest.vivaPay.dto.RepayResultRq
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/v1")
class PayController {

    @PostMapping("/BankCard/Repay")
    fun bankCardRepay(
        @RequestBody
        rq: RepayResultRq
    ): RepayResult {
        val result = RepayResult(
            clientId = rq.clientUid,
        paymentUrl = "/asd/dsgf",
        transaction = RepayResult.Transaction(
            id = 5,
            uuid = rq.transactionUid,
            status = "SUCCESS",
            dateTime = LocalDateTime.now().toString(),
            clientToken = rq.clientToken
        )
        )
        println("RepayResult $result")
        return result
    }

}
