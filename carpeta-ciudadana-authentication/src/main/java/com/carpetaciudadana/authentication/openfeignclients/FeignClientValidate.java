package com.carpetaciudadana.authentication.openfeignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carpetaciudadana.authentication.dto.UserPruebaSwagger;

@FeignClient(name="govcarpetaapp", url = "https://govcarpetaapp.mybluemix.net/apis/registerCitizen")
public interface FeignClientValidate {
	
	@PostMapping()
	String registerCitizen(@RequestBody UserPruebaSwagger user);

}
