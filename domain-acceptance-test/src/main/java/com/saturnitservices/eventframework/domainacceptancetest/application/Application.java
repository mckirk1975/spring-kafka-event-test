package com.saturnitservices.eventframework.domainacceptancetest.application;

import com.saturnitservices.eventframework.domainacceptancetest.controller.AcceptanceTestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public AcceptanceTestController acceptanceTestController() {
        return new AcceptanceTestController();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
