package com.carpetaciudadana.authentication.openfeignclients;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignInterceptor implements RequestInterceptor {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_TOKEN_TYPE = "Bearer";

	@Override
	public void apply(RequestTemplate template) {

		// Se lee el token de autenticación
		JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Desespero = " + token);
		System.out.println("Desespero = " + token.getToken().getTokenValue());

		if (token != null && token.getToken() != null) {
			// Se envía el token al servicio invocado
			template.header(AUTHORIZATION_HEADER,
					String.format("%s %s", BEARER_TOKEN_TYPE, token.getToken().getTokenValue()));
		}

	}

}
