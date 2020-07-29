package com.saturnitservices.eventframework.serviceone;

import com.saturnitservices.eventframework.serviceone.configuration.ServiceConfiguration;
import org.springframework.boot.SpringApplication;

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConfiguration.class, args);
	}
}
