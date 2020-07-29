package com.saturnitservices.eventframework.serviceonekstream.configuration;

import com.saturnitservices.eventframework.model.Event;
import com.saturnitservices.eventframework.pubsub.EventPublisher;
import com.saturnitservices.eventframework.serviceonekstream.model.ServiceOneData;
import com.saturnitservices.eventframework.serviceonekstream.process.TopicSubscriber;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class ServiceConfiguration {

    @Bean
    public Consumer<KStream<byte[], Event<ServiceOneData>>> topicOne(EventPublisher topicTwo) {
        return new TopicSubscriber("one", topicTwo);
    }

    @Bean
    public EventPublisher topicTwo() {

        return new EventPublisher();
    }
}
