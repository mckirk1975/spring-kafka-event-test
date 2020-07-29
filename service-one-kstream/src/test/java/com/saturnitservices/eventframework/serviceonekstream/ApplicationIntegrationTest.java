package com.saturnitservices.eventframework.serviceonekstream;


import com.saturnitservices.eventframework.serviceonekstream.configuration.ServiceConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ServiceConfiguration.class)
@EnabledIf(expression = "#{environment.acceptsProfiles('int-test', 'boo')}", loadContext = true)
public class ApplicationIntegrationTest {

    @Test
    public void testServiceOne() {

        assertTrue(false);
    }
}
