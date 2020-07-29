package com.saturnitservices.eventframework.domainacceptancetest.glue;

import com.saturnitservices.eventframework.domainacceptancetest.configuration.AcceptanceTestConfiguration;
import com.saturnitservices.eventframework.model.Event;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@CucumberContextConfiguration
@ContextConfiguration(classes = AcceptanceTestConfiguration.class)
public class EventSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSteps.class);

    @Autowired
    private KafkaTemplate<Integer, Event> kafkaTemplate;

    @Autowired
    private Consumer<Integer, Event> consumer;

    private Event event;  // TODO: might not be thread safe, might need ThreadLocal

    @Given("{string} event")
    public void event(String eventName) {

        LOGGER.debug("given {} event", eventName);

        event = new Event();
        event.setName(eventName);
    }

    @When("event is published to topic {string}")
    public void event_is_published_to_topic(String topic) {

        LOGGER.debug("when event is published to topic {}", topic);

        kafkaTemplate.send(topic, event);

        LOGGER.debug("published event to topic {}", topic);
    }

    @Then("{string} event is published to topic {string}")
    public void event_is_published_to_topic(String eventName, String topic) {

        LOGGER.debug("then {} event is published to topic {}", eventName, topic);

        consumer.subscribe(Arrays.asList(topic));
        ConsumerRecords<Integer, Event> records = KafkaTestUtils.getRecords(consumer, 5000L);

        records.forEach((consumerRecord) -> {

            LOGGER.debug("consumed: {}", consumerRecord.value());

        });




    }
}
