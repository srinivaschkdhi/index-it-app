package com.ot.indexitapp.controller;

import com.ot.indexitapp.model.FieldDefinition;
import com.ot.indexitapp.service.FieldDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/field-definitions")
public class FieldDefinitionController {

    @Autowired
    private FieldDefinitionService fieldDefinitionService;

    // Create field definitions for a specific document
    @PostMapping("/{documentId}")
    public ResponseEntity<List<FieldDefinition>> createFieldDefinitions(@PathVariable UUID documentId, @RequestBody List<FieldDefinition> definitions) {
        List<FieldDefinition> createdDefinitions = fieldDefinitionService.saveOrUpdateFieldDefinitions(documentId, definitions);
        return ResponseEntity.ok(createdDefinitions);
    }

    // Get all field definitions for a specific document
    @GetMapping("/{documentId}")
    public ResponseEntity<List<FieldDefinition>> getFieldDefinitions(@PathVariable UUID documentId) {
        List<FieldDefinition> definitions = fieldDefinitionService.findAllForDocument(documentId);
        return ResponseEntity.ok(definitions);
    }

    // Update a specific field definition
    @PutMapping("/{id}")
    public ResponseEntity<FieldDefinition> updateFieldDefinition(@PathVariable Long id, @RequestBody FieldDefinition definition) {
        FieldDefinition updatedDefinition = fieldDefinitionService.update(id, definition);
        return ResponseEntity.ok(updatedDefinition);
    }

    // Delete a specific field definition
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFieldDefinition(@PathVariable Long id) {
        fieldDefinitionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
