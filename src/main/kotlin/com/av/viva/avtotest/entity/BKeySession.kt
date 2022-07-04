package com.av.viva.avtotest.entity;


import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*


@Table("business_key_for_session")
class BKeySession (

    val id: UUID,

    val sessionId: String,

    val businessKey: String,

    val created: LocalDateTime
)
