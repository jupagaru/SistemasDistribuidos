package com.carpetaciudadana.authentication.dto;

import lombok.Data;

@Data
public class UserResponse {
	
	String token;
	String uid  ; 
	String documentId;

}
