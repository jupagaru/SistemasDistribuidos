package com.carpetaciudadana.users.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String uId;
	
	@NotNull
	private String email;

	@NotNull
	private String name;
	
	@NotNull
	private String lastName;

	private String address;

}
