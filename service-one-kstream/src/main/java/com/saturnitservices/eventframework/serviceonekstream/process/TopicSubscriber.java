package com.saturnitservices.eventframework.serviceonekstream.process;

import com.saturnitservices.eventframework.model.Event;
import com.saturnitservices.eventframework.pubsub.EventPublisher;
import com.saturnitservices.eventframework.serviceonekstream.model.ServiceOneData;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class TopicSubscriber implements Consumer<KStream<byte[], Event<ServiceOneData>>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicSubscriber.class);

    private final String topic;
    private final EventPublisher target;

    public TopicSubscriber(String topic, EventPublisher target) {
        this.topic = topic;
        this.target = target;
    }

    @Override
    public void accept(KStream<byte[], Event<ServiceOneData>> eventKStream) {
        eventKStream.peek((key, value) ->
                LOGGER.info("topic {} subscriber processing event: {}, data: {}, field1: {}, field2: {}", topic, value, value.getData(), value.getData().getField1(), value.getData().getField2()))
                .foreach((key, value) -> {value.incrementCount();
                                          if (value.getData().getField1().equals("bang")) throw new RuntimeException("BANG!");
                                          target.publishEvent(value);});
    }

}
