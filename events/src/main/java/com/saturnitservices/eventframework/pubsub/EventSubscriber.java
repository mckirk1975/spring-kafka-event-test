package com.saturnitservices.eventframework.pubsub;

import com.saturnitservices.eventframework.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public abstract class EventSubscriber implements Consumer<Event<?>>  {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSubscriber.class);

    @Override
    public void accept(Event<?> event) {

        LOGGER.info("received event: {}", event);

        processEvent(event);
    }

    public abstract void processEvent(Event<?> event);
}
