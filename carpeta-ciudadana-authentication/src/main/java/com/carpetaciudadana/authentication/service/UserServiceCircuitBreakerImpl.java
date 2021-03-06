package com.carpetaciudadana.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carpetaciudadana.authentication.dto.UserDTO;
import com.carpetaciudadana.authentication.openfeignclients.FeignClients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class UserServiceCircuitBreakerImpl implements UserServiceCircuitBreaker{

	@Autowired
	FeignClients feignClients;
	
	@Override
	@CircuitBreaker(name = "users", fallbackMethod = "fallbackSaveUser")
	public UserDTO saveUser(UserDTO user) throws Exception {
		return feignClients.save(user);
	}
	
	public UserDTO fallbackSaveUser(UserDTO user, Throwable e) throws Exception {
		throw new Exception("Lo siento, el servicio de USERS no se encuentra disponible " + e.getMessage());
	}

}
