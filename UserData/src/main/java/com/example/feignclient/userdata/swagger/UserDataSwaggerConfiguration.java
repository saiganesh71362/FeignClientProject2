package com.example.feignclient.userdata.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDataSwaggerConfiguration {

	@Bean
	public GroupedOpenApi controllerApi() {
		return GroupedOpenApi.builder()
				.group("UserData")
				.packagesToScan("com.example.feignclient.userdata.controller") // Specify  the package
				.build();
	}

}
