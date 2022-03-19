package com.carpetaciudadana.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carpetaciudadana.users.domain.Users;

@FeignClient(name="users")
public interface UserFeignClient {
	
	@GetMapping("users/search/find-username")
	public Users findByUsername(@RequestParam String username);

}
