package com.carpetaciudadana.authentication.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.carpetaciudadana.authentication.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Authentication Service API",
				"Authentication Service API Description",
				"1.0",
				"http://garciafranco.com/terms",
				new Contact("GarciaFranco", "https://garciafranco.com", "garciafranco@garciafranco.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}
}