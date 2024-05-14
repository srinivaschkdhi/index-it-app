package com.ot.indexitapp.repository;

import com.ot.indexitapp.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByUuid(String uuid);

}

