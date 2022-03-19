package com.carpetaciudadana.authentication.service;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carpetaciudadana.authentication.domain.Users;
import com.carpetaciudadana.authentication.repository.AuthRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthRepository authRepository;

	@Autowired
	Validator validator;

	@Transactional(readOnly = true)
	public Users findByEmailAndToken(String email, String token) throws Exception {
		if (email == null || token == null) {
			throw new Exception("Valores nulos");
		}

		if (authRepository.findByEmailAndToken(email, token) == null) {
			throw new Exception("El usuario no existe");
		}
		return authRepository.findByEmailAndToken(email, token);

	}

}
