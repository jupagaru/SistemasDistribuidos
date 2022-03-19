package com.carpetaciudadana.authentication.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	
	@NotNull
	private String id;
	
	@NotNull
	private String email;
	

}
