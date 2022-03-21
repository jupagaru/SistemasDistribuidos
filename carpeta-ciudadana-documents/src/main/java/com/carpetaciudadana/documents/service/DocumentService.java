package com.carpetaciudadana.documents.service;

import java.util.List;

import com.carpetaciudadana.documents.domain.Documents;
import com.carpetaciudadana.documents.dto.DocumentRequest;

public interface DocumentService extends GenericService<Documents, String>{

	List<Documents> findByIdUser(String idUser);
	
	List<String> validateDocuments(List<DocumentRequest> documentos);
	
}
