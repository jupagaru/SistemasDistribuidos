package com.carpetaciudadana.documents.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carpetaciudadana.documents.domain.Documents;

public interface DocumentRepository extends MongoRepository<Documents, String> {

	List<Documents> findByIdUser(String idUser);

	Documents findByIdUserAndFileName(String idUser, String fileName);

}
