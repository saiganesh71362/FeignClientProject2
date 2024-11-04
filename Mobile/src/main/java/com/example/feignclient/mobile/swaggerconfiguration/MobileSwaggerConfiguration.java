package com.example.feignclient.mobile.swaggerconfiguration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MobileSwaggerConfiguration {

	@Bean
	GroupedOpenApi controllerApi() {
		return GroupedOpenApi.builder()
				.group("Mobile")
				.packagesToScan("com.example.feignclient.mobile.controller") // Specify the package
				.build();
	}
}
