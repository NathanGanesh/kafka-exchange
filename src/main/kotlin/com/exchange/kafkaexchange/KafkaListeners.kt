package com.exchange.kafkaexchange

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListeners {
    @KafkaListener(topics = ["exchange"], groupId = "market_exchange")
    fun listener(data: Message) {
        print("got data " + data.message + " " + data.created.toString())
    }
}
