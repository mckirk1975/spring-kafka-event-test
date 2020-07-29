package com.saturnitservices.eventframework.serviceonekstream.serde;

import com.fasterxml.jackson.core.type.TypeReference;
import com.saturnitservices.eventframework.model.Event;
import com.saturnitservices.eventframework.serviceonekstream.model.ServiceOneData;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class ServiceOneDataSerde extends JsonSerde<Event<ServiceOneData>> {

    public ServiceOneDataSerde() {
        super(new JsonSerializer<>(),
                new JsonDeserializer<>(new TypeReference<Event<ServiceOneData>>() {}));
    }
}
