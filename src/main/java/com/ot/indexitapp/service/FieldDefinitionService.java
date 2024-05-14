package com.ot.indexitapp.service;

import com.ot.indexitapp.model.Document;
import com.ot.indexitapp.model.FieldDefinition;
import com.ot.indexitapp.repository.DocumentRepository;
import com.ot.indexitapp.repository.FieldDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FieldDefinitionService {

    @Autowired
    private FieldDefinitionRepository fieldDefinitionRepository;

    @Autowired
    private DocumentRepository documentRepository;


    @Transactional
    public List<FieldDefinition> saveOrUpdateFieldDefinitions(UUID documentUUID, List<FieldDefinition> newDefinitions) {
        Document document = documentRepository.findByUuid(documentUUID.toString())
                .orElseGet(() -> {
                    Document newDoc = new Document();
                    newDoc.setUuid(documentUUID.toString());
                    return documentRepository.save(newDoc);
                });

        // Delete old definitions associated to the document
        fieldDefinitionRepository.deleteByDocumentUuid(documentUUID.toString());

        // For each new definition, set its document field and save it to the repository
        newDefinitions.forEach(definition -> definition.setDocument(document));
        return fieldDefinitionRepository.saveAll(newDefinitions);
    }


    // Find all field definitions for a specific document
    public List<FieldDefinition> findAllForDocument(UUID documentId) {
        return fieldDefinitionRepository.findByDocumentUuid(documentId.toString());
    }

    // Update a specific field definition
    public FieldDefinition update(Long id, FieldDefinition newDetails) {
        FieldDefinition existingDefinition = fieldDefinitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field Definition not found with id: " + id));
        existingDefinition.setName(newDetails.getName());
        existingDefinition.setDataType(newDetails.getDataType());
        return fieldDefinitionRepository.save(existingDefinition);
    }

    // Delete a specific field definition
    public void delete(Long id) {
        fieldDefinitionRepository.deleteById(id);
    }
}
