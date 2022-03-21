package com.carpetaciudadana.documents.dto;

import lombok.Data;

@Data
public class DocumentDTO {

	String id;
	String idUser;
	String url;
	String fileName;
	String fileSize;
	String fileType;
	String date;
}
