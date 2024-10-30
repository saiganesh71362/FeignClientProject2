package com.example.feignclient.sim.swaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimSwaggerConfiguration {

	@Bean
	GroupedOpenApi controllerApi() {
		return GroupedOpenApi.builder()
				.group("Sim")
				.packagesToScan("com.example.feignclient.sim.controller") // Specify the package
				.build();
	}

}
