package com.saturnitservices.eventframework.domainacceptancetest.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
                 glue = "com.saturnitservices.eventframework.domainacceptancetest.glue")
public class AcceptanceTest {
}
