package com.av.viva.avtotest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class AvtotestApplication

fun main(args: Array<String>) {
    runApplication<AvtotestApplication>(*args)
}
