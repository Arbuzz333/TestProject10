package com.av.viva.avtotest.rest.mobile

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/viva/v1")
class MessageController {

    @PostMapping("/messages/receive")
    fun receiveMessage(
        @RequestBody
        jsonNode: String?
    ): ResponseEntity<*> {
        println("Message receive $jsonNode")
        return ResponseEntity.ok().body("Test OK")
    }
}