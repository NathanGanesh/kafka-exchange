package com.exchange.kafkaexchange

import java.time.LocalDateTime

class Message(
    val message: String,
    val created: LocalDateTime
)
