package com.ot.indexitapp.service;


import com.ot.indexitapp.model.IndexField;
import com.ot.indexitapp.repository.IndexFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexFieldService {

    @Autowired
    private IndexFieldRepository repository;

    public List<IndexField> saveAll(List<IndexField> fields) {
        return repository.saveAll(fields);
    }
}
