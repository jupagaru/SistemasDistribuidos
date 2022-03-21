package com.carpetaciudadana.documents.openfeignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="govcarpetaapp", url = "https://govcarpetaapp.mybluemix.net/apis")

public interface FeignClientValidate {
	
	@GetMapping("/authenticateDocument/{id}/{urlDocument}/{documentTitle}")
	String validateDocument(@PathVariable String id, @PathVariable String urlDocument, @PathVariable String documentTitle);

}
