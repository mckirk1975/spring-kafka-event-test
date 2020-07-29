package com.saturnitservices.eventframework.serviceonekstream;

import com.saturnitservices.eventframework.serviceonekstream.configuration.ServiceConfiguration;
import org.springframework.boot.SpringApplication;

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConfiguration.class, args);
	}
}
