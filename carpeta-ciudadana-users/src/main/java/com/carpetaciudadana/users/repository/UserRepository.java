package com.carpetaciudadana.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carpetaciudadana.users.domain.Users;

public interface UserRepository extends MongoRepository<Users, String>{
	
	Users findByEmail(String email);

}
