package com.carpetaciudadana.oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carpetaciudadana.oauth.clients.UserFeignClient;
import com.carpetaciudadana.users.domain.Users;

@Service
public class UserService implements UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userFeignClient.findByUsername(username);

		if(user == null) {
			log.error("\"Error en el login, no existe el usuario " + username + " en el sistema.");
			throw new UsernameNotFoundException("Error en el login, no existe el usuario " + username + " en el sistema.  ");
		}
		
		log.info("Usuario autenticado: " + username);
		
		return new User(user.getEmail(), user.getToken(), true, true, true, true, null);
	}

}