package com.carpetaciudadana.authentication.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpetaciudadana.authentication.config.KeycloakConfig;
import com.carpetaciudadana.authentication.dto.UserDTO;
import com.carpetaciudadana.authentication.dto.UserResponse;
import com.carpetaciudadana.authentication.openfeignclients.FeignClients;

@Service
public class KeyCloakServiceImpl {

	/*@Autowired
	UserServiceCircuitBreaker userServiceCircuitBreaker;*/
	
	@Autowired
	FeignClients feignClients;

	public void addUser(UserDTO user) {
		UsersResource usersResource = KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
		CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

		UserRepresentation kcUser = new UserRepresentation();
		kcUser.setUsername(user.getUserName());
		kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
		kcUser.setFirstName(user.getFirstName());
		kcUser.setLastName(user.getLastName());
		kcUser.setEmail(user.getEmail());
		kcUser.setEnabled(true);
		kcUser.setEmailVerified(true);
		usersResource.create(kcUser);
		try {
			saveUserFeign(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private UserDTO saveUserFeign(UserDTO user) throws Exception {
		return feignClients.save(user);
	}

	private static CredentialRepresentation createPasswordCredentials(String password) {
		CredentialRepresentation passwordCredentials = new CredentialRepresentation();
		passwordCredentials.setTemporary(false);
		passwordCredentials.setType(CredentialRepresentation.PASSWORD);
		passwordCredentials.setValue(password);
		return passwordCredentials;
	}

	public UserResponse getUser(String userName) {
		UsersResource usersResource = getInstance();
		List<UserRepresentation> user = usersResource.search(userName, true);
		UserResponse userResponse = new UserResponse();
		if (user != null) {
			userResponse.setToken(KeycloakConfig.getToken());
		}
		return userResponse;

	}

	public void deleteUser(String userId) {
		UsersResource usersResource = getInstance();
		usersResource.get(userId).remove();
	}

	public void sendVerificationLink(String userId) {
		UsersResource usersResource = getInstance();
		usersResource.get(userId).sendVerifyEmail();
	}

	public void sendResetPassword(String userId) {
		UsersResource usersResource = getInstance();

		usersResource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
	}

	public UsersResource getInstance() {
		return KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
	}

}
