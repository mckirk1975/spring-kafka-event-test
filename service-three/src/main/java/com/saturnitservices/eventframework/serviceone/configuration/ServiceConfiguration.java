package com.saturnitservices.eventframework.serviceone.configuration;

import com.saturnitservices.eventframework.pubsub.EventPublisher;
import com.saturnitservices.eventframework.serviceone.process.TopicSubscriber;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceConfiguration {

    @Bean
    public TopicSubscriber topicThree(EventPublisher topicOne) {
        return new TopicSubscriber("three", topicOne);
    }

    @Bean
    public EventPublisher topicOne() {
        return new EventPublisher();
    }

}
