package com.exchange.kafkaexchange

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/messages")
class MessageController(val kafkaTemplate: KafkaTemplate<String, Message >) {
    @PostMapping
    fun publish(@RequestBody messageRequest: MessageRequest) {
        var message = Message(messageRequest.message, LocalDateTime.now())
        kafkaTemplate.send("exchange", message)
    }
}
