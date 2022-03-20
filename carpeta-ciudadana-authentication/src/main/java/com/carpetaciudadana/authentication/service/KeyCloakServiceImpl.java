package com.carpetaciudadana.authentication.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import com.carpetaciudadana.authentication.config.Credentials;
import com.carpetaciudadana.authentication.config.KeycloakConfig;
import com.carpetaciudadana.authentication.dto.User;
import com.carpetaciudadana.authentication.dto.UserDTO;

import lombok.AllArgsConstructor;

@Service
public class KeyCloakServiceImpl {

	public void addUser(User user) {
		UsersResource usersResource = KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
		CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

		UserRepresentation kcUser = new UserRepresentation();
		kcUser.setUsername(user.getEmail());
		kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
		kcUser.setFirstName(user.getFirstName());
		kcUser.setLastName(user.getLastName());
		kcUser.setEmail(user.getEmail());
		kcUser.setEnabled(true);
		kcUser.setEmailVerified(false);
		usersResource.create(kcUser);

	}

	private static CredentialRepresentation createPasswordCredentials(String password) {
		CredentialRepresentation passwordCredentials = new CredentialRepresentation();
		passwordCredentials.setTemporary(false);
		passwordCredentials.setType(CredentialRepresentation.PASSWORD);
		passwordCredentials.setValue(password);
		return passwordCredentials;
	}

	public List<UserRepresentation> getUser(String userName) {
		UsersResource usersResource = getInstance();
		List<UserRepresentation> user = usersResource.search(userName, true);
		return user;

	}

	public void updateUser(String userId, UserDTO userDTO) {
		CredentialRepresentation credential = Credentials.createPasswordCredentials(userDTO.getPassword());
		UserRepresentation user = new UserRepresentation();
		user.setUsername(userDTO.getUserName());
		user.setFirstName(userDTO.getFirstname());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmailId());
		user.setCredentials(Collections.singletonList(credential));

		UsersResource usersResource = getInstance();
		usersResource.get(userId).update(user);
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
