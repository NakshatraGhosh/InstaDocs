package com.InstaDoc.demo.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import com.InstaDoc.demo.Models.AppointmentBookingRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.listener.ContainerProperties;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, AppointmentBookingRequest> consumerFactory() {
        JsonDeserializer<AppointmentBookingRequest> deserializer = new JsonDeserializer<>();
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                Map.of(
                    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                    ConsumerConfig.GROUP_ID_CONFIG, "booking-group"
                ),
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AppointmentBookingRequest> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AppointmentBookingRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3); // Handle 3 messages in parallel
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        return factory;
    }

    @Bean
    public ProducerFactory<String, AppointmentBookingRequest> producerFactory() {
        return new DefaultKafkaProducerFactory<>(
                Map.of(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
                )
        );
    }

    @Bean
    public KafkaTemplate<String, AppointmentBookingRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
