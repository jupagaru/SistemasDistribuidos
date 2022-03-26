package com.carpetaciudadana.authentication.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakConfig {

	static Keycloak keycloak = null;
    //final static String serverUrl = "http://localhost:31002/auth";
    final static String serverUrl = "http://localhost:8080/auth";
    public final static String realm = "master";
    final static String clientId = "carpeta-ciudadana";
    //final static String clientSecret = "66b4d569-2ac6-4a35-9f91-3a7d27fd0380";
    final static String clientSecret = "3e32405d-e9f2-46a4-a449-e53df4325a2c";
    final static String userName = "admin";
    final static String password = "admin";

    public KeycloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){
           
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                                   .connectionPoolSize(10)
                                   .build()
                                   )
                    .build();
        }
        return keycloak;
    }
    
    public static String getToken() {
    	return keycloak.tokenManager().getAccessTokenString();
    }

}
