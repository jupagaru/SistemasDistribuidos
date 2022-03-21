package com.carpetaciudadana.documents.dto;

import lombok.Data;

@Data
public class DocumentRequest {
	
	private String uId;
	private String documentId;
	private String fileUrl;
	private String fileName;

}
