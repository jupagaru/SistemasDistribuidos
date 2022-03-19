package com.carpetaciudadana.authentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carpetaciudadana.authentication.domain.Users;



public interface AuthRepository extends MongoRepository<Users, String>{
	
	Users findByEmailAndToken(String email, String token);


}