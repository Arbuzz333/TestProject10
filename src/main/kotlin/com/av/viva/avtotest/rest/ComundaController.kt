package com.av.viva.avtotest.rest

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/engine-rest")
class ComundaController {

    @PostMapping("/message")
    fun message(
        @RequestBody
        jsonNode: JsonNode
    ) {
        println(jsonNode)
        println("\n")
    }
}