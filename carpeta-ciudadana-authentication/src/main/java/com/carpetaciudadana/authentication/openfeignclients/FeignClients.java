package com.carpetaciudadana.authentication.openfeignclients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carpetaciudadana.authentication.dto.UserDTO;

@FeignClient(value = "api-gateway")
public interface FeignClients {

	

	@PostMapping("/users/api/v1/users")
	public UserDTO save(@Valid @RequestBody UserDTO userDTO) throws Exception;
	
	@GetMapping("/users/api/v1/users/email/{email}")
	public UserDTO findByEmail(@PathVariable("email") String email) throws Exception;
	
}
