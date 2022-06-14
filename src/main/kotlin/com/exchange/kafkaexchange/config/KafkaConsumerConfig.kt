package com.exchange.kafkaexchange.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.protocol.Message
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerConfig {
    @Value("localhost:9092")
    lateinit var bootstrapServers: String

    private fun consumerConfig(): Map<String, Any> {
        val producerProps = mutableMapOf<String, Any>()
        producerProps[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        producerProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        producerProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return producerProps
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Message> {
        var jsonDeserializer: JsonDeserializer<Message> = JsonDe
        jsonDeserializer.addTrustedPackages("com.exchange")
        return DefaultKafkaConsumerFactory(consumerConfig(), JsonDeserializer(), jsonDeserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>> {
        var factory: ConcurrentKafkaListenerContainerFactory<String, Message>  = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}