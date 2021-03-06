package com.exchange.kafkaexchange.config

import com.exchange.kafkaexchange.Message
import org.apache.kafka.clients.consumer.ConsumerConfig
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
        return producerProps
    }

    @Bean
    fun consumerFactory(): DefaultKafkaConsumerFactory<String, com.exchange.kafkaexchange.Message> {
        var jsonDeserializer: JsonDeserializer<Message> = JsonDeserializer()
        jsonDeserializer.addTrustedPackages("com.exchange")
        return DefaultKafkaConsumerFactory(consumerConfig(), StringDeserializer(), jsonDeserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, com.exchange.kafkaexchange.Message>> {
        var factory: ConcurrentKafkaListenerContainerFactory<String, Message>  = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}