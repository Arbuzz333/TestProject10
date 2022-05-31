package com.av.viva.avtotest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@ConstructorBinding
@ConfigurationProperties(prefix = "app-test")
data class AppTestProperties(

    val isSbpPossibilityTransfer: Boolean,
    val isTkbCardData: Boolean
)
