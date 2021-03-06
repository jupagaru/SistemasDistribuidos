package com.carpetaciudadana.authentication.service;

import org.keycloak.admin.client.resource.UsersResource;

import com.carpetaciudadana.authentication.dto.UserDTO;
import com.carpetaciudadana.authentication.dto.UserRequest;
import com.carpetaciudadana.authentication.dto.UserResponse;

public interface KeyCloakService {

	public void addUser(UserDTO user);

	public UserResponse getUser(UserRequest userRequest);

	public void deleteUser(String userId);

	public void sendVerificationLink(String userId);

	public void sendResetPassword(String userId);

	public UsersResource getInstance();

}
