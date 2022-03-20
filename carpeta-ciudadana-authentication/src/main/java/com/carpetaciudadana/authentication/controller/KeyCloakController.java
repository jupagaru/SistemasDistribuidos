package com.carpetaciudadana.authentication.controller;

import java.util.List;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carpetaciudadana.authentication.dto.User;
import com.carpetaciudadana.authentication.dto.UserDTO;
import com.carpetaciudadana.authentication.service.KeyCloakServiceImpl;

@RestController
@RequestMapping("/api/v1/user")
public class KeyCloakController {

	@Autowired
	KeyCloakServiceImpl service;

	@PostMapping("/prueba")
	public String addUserPrueba(@RequestBody UserDTO userDTO) {
		System.out.println("Entramos al controller");
		return "User Added Successfully.";
	}

	@PostMapping()
	public String addUser(@RequestBody User userDTO) {
		service.addUser(userDTO);
		return "User Added Successfully.";
	}

	@GetMapping("/{userName}")
	public List<UserRepresentation> getUser(@PathVariable("userName") String userName) {
		List<UserRepresentation> user = service.getUser(userName);
		return user;
	}

	@PutMapping("/update/{userId}")
	public String updateUser(@PathVariable("userId") String userId, @RequestBody UserDTO userDTO) {
		service.updateUser(userId, userDTO);
		return "User Details Updated Successfully.";
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
