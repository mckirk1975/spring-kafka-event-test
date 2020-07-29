package com.saturnitservices.eventframework.serviceone.process;

import com.saturnitservices.eventframework.model.Event;
import com.saturnitservices.eventframework.pubsub.EventPublisher;
import com.saturnitservices.eventframework.pubsub.EventSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicSubscriber extends EventSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicSubscriber.class);

    private final String topic;
    private final EventPublisher target;

    public TopicSubscriber(String topic, EventPublisher target) {
        this.topic = topic;
        this.target = target;
    }

    @Override
    public void processEvent(Event event) {

        LOGGER.info("topic {} subscriber processing event: {}", topic, event);
        event.incrementCount();
        target.publishEvent(event);
    }
}
