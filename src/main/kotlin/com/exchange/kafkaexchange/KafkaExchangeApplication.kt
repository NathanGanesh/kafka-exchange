package com.exchange.kafkaexchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaExchangeApplication fun main(args: Array<String>) {
    runApplication<KafkaExchangeApplication>(*args)
}
//    @Bean
//    fun init(kafkaTemplate: KafkaTemplate<String, String>) = CommandLineRunner { (args) ->
//            kafkaTemplate.send("exchange", "hello kafka")
//    }

// @EnableEurekaClient
// class NotificationApplication
//
// fun main(args: Array<String>) {
//     runApplication<NotificationApplication>(*args)
// }
//
//
//
// @Bean
// fun init(rabbitMqMessageProducer: RabbitMQMessageProducerToBeDeleted, notificationConfig: NotificationConfig) = CommandLineRunner { (args) ->
//     run {
//         rabbitMqMessageProducer.publish(
//             "foo", notificationConfig.internalExchange,
//             notificationConfig.internalNotificationRoutingKey
//         )
//     }
// }
