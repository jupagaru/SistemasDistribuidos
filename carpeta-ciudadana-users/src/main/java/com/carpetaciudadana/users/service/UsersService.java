package com.carpetaciudadana.users.service;

import java.util.List;

import com.carpetaciudadana.users.domain.Users;

public interface UsersService extends GenericService<Users, String> {

	Users findByEmail(String email);

}
