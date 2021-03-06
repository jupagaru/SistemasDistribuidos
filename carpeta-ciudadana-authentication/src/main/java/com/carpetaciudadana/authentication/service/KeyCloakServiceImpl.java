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
import com.carpetaciudadana.authentication.dto.UserPruebaSwagger;
import com.carpetaciudadana.authentication.dto.UserRequest;
import com.carpetaciudadana.authentication.dto.UserResponse;
import com.carpetaciudadana.authentication.openfeignclients.FeignClientValidate;
import com.carpetaciudadana.authentication.openfeignclients.FeignClients;

@Service
public class KeyCloakServiceImpl {

	/*@Autowired
	UserServiceCircuitBreaker userServiceCircuitBreaker;*/
	
	@Autowired
	FeignClients feignClients;
	
	@Autowired
	FeignClientValidate feignClientValidate;

	public String addUser(UserDTO user) {
		UsersResource usersResource = KeycloakConfig.getInstance().realm(KeycloakConfig.realm).users();
		CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

		UserRepresentation kcUser = new UserRepresentation();
		kcUser.setUsername(user.getEmail());
		kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
		kcUser.setFirstName(user.getFirstName());
		kcUser.setLastName(user.getLastName());
		kcUser.setEmail(user.getEmail());
		kcUser.setEnabled(true);
		kcUser.setEmailVerified(true);

		try {
			String userRegister = feignClientValidate.validateCitizen(Integer.parseInt(user.getNumIdentificacion()));
			if (userRegister == null) {
				usersResource.create(kcUser);
				saveUserFeign(user);
				return "User Added Successfully.";
			}else {
				return userRegister;
			}
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	private UserDTO saveUserFeign(UserDTO user) throws Exception {
		UserPruebaSwagger userSwagger = new UserPruebaSwagger();
		
		userSwagger.setId(user.getNumIdentificacion());
		userSwagger.setEmail(user.getEmail());
		userSwagger.setName(user.getFirstName());
		userSwagger.setAddress(user.getAddress());
		userSwagger.setOperatorId("4672"); // TODO Defiir en las propiedades 
		userSwagger.setOperatorName("Operador Franco Garcia"); // TODO Defiir en las propiedades
		
		// TODO Descomentar para que vaya al centralizador
		String respuesta = feignClientValidate.registerCitizen(userSwagger);
		System.out.println("Respuesta de swagger = " + respuesta);
				
		return feignClients.save(user);
	}

	private static CredentialRepresentation createPasswordCredentials(String password) {
		CredentialRepresentation passwordCredentials = new CredentialRepresentation();
		passwordCredentials.setTemporary(false);
		passwordCredentials.setType(CredentialRepresentation.PASSWORD);
		passwordCredentials.setValue(password);
		return passwordCredentials;
	}

	public UserResponse getUser(UserRequest userRequest) {
		UsersResource usersResource = getInstance();
		List<UserRepresentation> user = usersResource.search(userRequest.getEmail(), true);
		UserResponse userResponse = new UserResponse();
		if (user != null) {
			userResponse.setToken(KeycloakConfig.getToken());
			try {
				UserDTO userfeig = getUserFeign(userRequest.getEmail());
				System.out.println("userFeig = " + userfeig);
				userResponse.setUid(userfeig.getUId());
				userResponse.setDocumentId(userfeig.getNumIdentificacion());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userResponse;

	}
	
	private UserDTO getUserFeign(String email) throws Exception {
		
		return feignClients.findByEmail(email);
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
