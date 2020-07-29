package com.saturnitservices.eventframework.pubsub;

import com.saturnitservices.eventframework.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;

public class EventPublisher implements Supplier<Event<?>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);

    private BlockingQueue<Event<?>> events = new LinkedBlockingDeque<>();

    public Event<?> get() {

        try {
            Event<?> event = events.take();
            LOGGER.info("publishing event: {}", event);
            return event;
        } catch (InterruptedException e) {
            LOGGER.warn("exception: {}", e);
            return null;
        }
    }

    public void publishEvent(Event<?> event) {

        events.add(event);
    }
}
