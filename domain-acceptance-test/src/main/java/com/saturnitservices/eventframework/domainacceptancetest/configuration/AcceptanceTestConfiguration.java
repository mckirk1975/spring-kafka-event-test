package com.saturnitservices.eventframework.domainacceptancetest.configuration;

import com.saturnitservices.eventframework.model.Event;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public class AcceptanceTestConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptanceTestConfiguration.class);

    @Bean
    public KafkaTemplate<Integer, Event> kafkaTemplate(@Value("${spring.kafka.bootstrap-servers:localhost:9092}") String bootstrapServers) {

        LOGGER.debug("Creating kafkaTemplate");

        // See https://kafka.apache.org/documentation/#producerconfigs for more properties

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new KafkaTemplate<Integer, Event>(new DefaultKafkaProducerFactory<>(props));
    }


    @Bean
    public Consumer<Integer, Event> consumer(@Value("${spring.kafka.bootstrap-servers:localhost:9092}") String bootstrapServers) {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer valueDeserializer = new JsonDeserializer(Event.class);
        DefaultKafkaConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory(props);
        consumerFactory.setValueDeserializer(valueDeserializer);
        return consumerFactory.createConsumer("test-group", "test-client");
    }

}
