package com.carpetaciudadana.authentication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carpetaciudadana.authentication.domain.Users;
import com.carpetaciudadana.authentication.dto.AuthRequest;
import com.carpetaciudadana.authentication.dto.AuthResponse;
import com.carpetaciudadana.authentication.mapper.AuthMapper;
import com.carpetaciudadana.authentication.service.AuthService;



@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	AuthMapper authMapper;
	
	@PostMapping
	public AuthResponse findById(@Valid @RequestBody AuthRequest authRequest) throws Exception{
		Users user = authService.findByEmailAndToken(authRequest.getEmail(), authRequest.getToken());
		return authMapper.userstoAuthResponse(user);
	}

}
