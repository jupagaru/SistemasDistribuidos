package com.carpetaciudadana.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carpetaciudadana.authentication.dto.UserDTO;
import com.carpetaciudadana.authentication.dto.UserRequest;
import com.carpetaciudadana.authentication.dto.UserResponse;
import com.carpetaciudadana.authentication.service.KeyCloakServiceImpl;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class KeyCloakController {

	@Autowired
	KeyCloakServiceImpl service;

	@PostMapping()
	public String addUser(@RequestBody UserDTO userDTO) {
		service.addUser(userDTO);
		return "User Added Successfully.";
	}

	@PostMapping("/login")
	public UserResponse getUser(@RequestBody UserRequest userRequest) {
		UserResponse user = service.getUser(userRequest);
		return user;
	}


	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable("userId") String userId) {
		service.deleteUser(userId);
		return "User Deleted Successfully.";
	}

	@GetMapping("/verification-link/{userId}")
	public String sendVerificationLink(@PathVariable("userId") String userId) {
		service.sendVerificationLink(userId);
		return "Verification Link Send to Registered E-mail Id.";
	}

	@GetMapping("/reset-password/{userId}")
	public String sendResetPassword(@PathVariable("userId") String userId) {
		service.sendResetPassword(userId);
		return "Reset Password Link Send Successfully to Registered E-mail Id.";
	}
}
