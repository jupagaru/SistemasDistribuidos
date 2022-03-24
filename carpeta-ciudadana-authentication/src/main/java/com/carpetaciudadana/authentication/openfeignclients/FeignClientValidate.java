package com.carpetaciudadana.authentication.openfeignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carpetaciudadana.authentication.dto.UserPruebaSwagger;

@FeignClient(name="govcarpetaapp", url = "https://govcarpetaapp.mybluemix.net/apis")
public interface FeignClientValidate {
	
	@PostMapping("/registerCitizen")
	String registerCitizen(@RequestBody UserPruebaSwagger user);
	
	@GetMapping("/validateCitizen/{id}")
	String validateCitizen(@PathVariable Integer id);

}
