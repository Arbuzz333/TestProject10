package com.av.viva.avtotest.rest.mobile

import com.av.viva.avtotest.rest.dto.NotificationRq
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/viva/v1")
class MessageController {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MessageController::class.java)
    }

    @PostMapping("/messages/receive")
    fun receiveMessage(
        @RequestBody
        jsonNode: String?
    ): ResponseEntity<*> {
        logger.info("Message receive $jsonNode")
        return ResponseEntity.ok().body("Test OK")
    }

    @PostMapping("/notify/send",
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun notifySend(
        rq: NotificationRq?
    ): ResponseEntity<*> {
        logger.info("Message notify $rq")
        return ResponseEntity.ok().body("{\"code\":200}")
    }
}