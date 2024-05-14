package com.ot.indexitapp.repository;


import com.ot.indexitapp.model.FieldDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldDefinitionRepository extends JpaRepository<FieldDefinition, Long> {
    List<FieldDefinition> findByDocumentUuid(String documentId);
    void deleteByDocumentUuid(String uuid);
}
