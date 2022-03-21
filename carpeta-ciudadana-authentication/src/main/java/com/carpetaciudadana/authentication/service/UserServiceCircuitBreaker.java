package com.carpetaciudadana.authentication.service;

import com.carpetaciudadana.authentication.dto.UserDTO;

public interface UserServiceCircuitBreaker {
	
	public UserDTO saveUser(UserDTO user) throws Exception;

}
