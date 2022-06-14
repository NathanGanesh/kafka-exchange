package com.exchange.kafkaexchange.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.util.*

@Configuration
@PropertySource("classpath:application.properties")
class KafkaProducerConfig {
    //        mutableMapOf(
//        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
//        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer,
// ProducerConfig        cl"securityClassId" to transactionSecurityClassId,
//        "splitFactor" to helper.defaultSplitFactor
//    )
    @Value("localhost:9092")
    lateinit var bootstrapServers: String
//    lateinit var producerConfig: Map<String, Any> {
//        val producerProps= MutableMap<String, Any>
//
//        return producerProps
//    }

    private fun producerConfig(): Map<String, Any> {
        val producerProps = mutableMapOf<String, Any>()
        producerProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        producerProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        producerProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return producerProps
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, String>): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory)
    }
}
