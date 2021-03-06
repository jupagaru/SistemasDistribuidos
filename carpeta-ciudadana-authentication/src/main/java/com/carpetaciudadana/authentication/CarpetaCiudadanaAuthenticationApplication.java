package com.carpetaciudadana.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class CarpetaCiudadanaAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarpetaCiudadanaAuthenticationApplication.class, args);
	}

}
