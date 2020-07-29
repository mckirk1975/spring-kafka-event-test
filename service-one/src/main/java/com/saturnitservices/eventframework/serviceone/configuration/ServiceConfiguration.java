package com.saturnitservices.eventframework.serviceone.configuration;

import com.saturnitservices.eventframework.model.Event;
import com.saturnitservices.eventframework.pubsub.EventPublisher;
import com.saturnitservices.eventframework.serviceone.process.TopicSubscriber;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.function.Function;

@SpringBootApplication
public class ServiceConfiguration {

    @Bean
    public TopicSubscriber topicOne(EventPublisher topicTwo) {
        return new TopicSubscriber("one", topicTwo);
    }

    @Bean
    public EventPublisher topicTwo() {
        return new EventPublisher();
    }

    @Bean
    public Function<Tuple2<Flux<Event>, Flux<Event>>, Flux<Event>> gather() {
        return tuple -> {
            Flux<Event> stringStream = tuple.getT1();
            Flux<Event> intStream = tuple.getT2();
            return Flux.merge(stringStream, intStream);
        };
    }

}
