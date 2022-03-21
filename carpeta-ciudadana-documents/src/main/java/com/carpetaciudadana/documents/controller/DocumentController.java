package com.carpetaciudadana.documents.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carpetaciudadana.documents.domain.Documents;
import com.carpetaciudadana.documents.dto.DocumentDTO;
import com.carpetaciudadana.documents.dto.DocumentRequest;
import com.carpetaciudadana.documents.mapper.DocumentMapper;
import com.carpetaciudadana.documents.service.DocumentService;

@RestController
@RequestMapping("/api/v1/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

	@Autowired
	DocumentService documentService;

	@Autowired
	DocumentMapper documentMapper;

	@PostMapping
	public ResponseEntity<DocumentDTO> save(@Valid @RequestBody DocumentDTO documentDTO) throws Exception {
		Documents documents = documentMapper.documentDTOtoDocuments(documentDTO);
		documents = documentService.save(documents);
		documentDTO = documentMapper.documentstoDocumentDTO(documents);
		return ResponseEntity.ok().body(documentDTO);
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<List<DocumentDTO>> findByIdUser(@PathVariable String idUser) {
		List<Documents> documentos = documentService.findByIdUser(idUser);
		List<DocumentDTO> documentosDTO = documentMapper.documentsListTodocumentDTOList(documentos);
		return ResponseEntity.ok().body(documentosDTO);
	}

	@PostMapping("/validateDocuments")
	public ResponseEntity<List<String>> validateDocuments(@RequestBody List<DocumentRequest> documents) {
		List<String> resultados = documentService.validateDocuments(documents);
		
		return ResponseEntity.ok().body(resultados);
	}

}
