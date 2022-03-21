package com.carpetaciudadana.documents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CarpetaCiudadanaDocumentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarpetaCiudadanaDocumentsApplication.class, args);
	}

}
