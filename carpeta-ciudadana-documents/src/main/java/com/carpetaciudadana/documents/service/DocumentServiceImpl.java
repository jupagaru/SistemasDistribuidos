package com.carpetaciudadana.documents.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carpetaciudadana.documents.domain.Documents;
import com.carpetaciudadana.documents.dto.DocumentRequest;
import com.carpetaciudadana.documents.openfeignclients.FeignClientValidate;
import com.carpetaciudadana.documents.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	Validator validator;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	FeignClientValidate feignClient;

	@Override
	@Transactional(readOnly = true)
	public List<Documents> findAll() {
		return documentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Documents> findById(String id) {
		return documentRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Documents save(Documents entity) throws Exception {
		if (entity == null) {
			throw new Exception("El Documento es nulo");
		}
		validate(entity);
		if (documentRepository.findByIdUserAndFileName(entity.getIdUser(), entity.getFileName()) != null) {
			throw new Exception("Ya existe un documento con ese nombre");
		}
		return documentRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Documents update(Documents entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Documents entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Documents entity) throws Exception {
		Set<ConstraintViolation<Documents>> constraintViolations = validator.validate(entity);
		if (constraintViolations.isEmpty() == false) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return documentRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Documents> findByIdUser(String idUser) {
		return documentRepository.findByIdUser(idUser);
	}

	@Override
	public List<String> validateDocuments(List<DocumentRequest> documentos) {
		List<String> respuestas = new ArrayList<String>();
		if (documentos != null) {
			documentos.stream().forEach((p) -> {
				String message = feignClient.validateDocument(p.getDocumentId(), p.getFileUrl(), p.getFileName());
				respuestas.add(message);

			});
		}

		return respuestas;
	}

}
