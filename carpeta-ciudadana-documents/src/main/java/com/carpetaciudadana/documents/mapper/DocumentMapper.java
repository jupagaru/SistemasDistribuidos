package com.carpetaciudadana.documents.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.carpetaciudadana.documents.domain.Documents;
import com.carpetaciudadana.documents.dto.DocumentDTO;


@Mapper
public interface DocumentMapper {

	public DocumentDTO documentstoDocumentDTO(Documents documents);

	public Documents documentDTOtoDocuments(DocumentDTO documenDTO);

	public List<DocumentDTO> documentsListTodocumentDTOList(List<Documents> documents);

	public List<Documents> documentDTOListToDocumentsList(List<DocumentDTO> documentDTO);
}
