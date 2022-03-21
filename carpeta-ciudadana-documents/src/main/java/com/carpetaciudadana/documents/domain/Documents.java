package com.carpetaciudadana.documents.domain;

import lombok.Data;

@Data
public class Documents {
	
	String id;
	String idUser;
	String url;
	String fileName;
	String fileSize;
	String fileType;
	String date;

}
