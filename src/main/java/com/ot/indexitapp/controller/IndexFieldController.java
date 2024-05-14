package com.ot.indexitapp.controller;


import com.ot.indexitapp.model.IndexField;
import com.ot.indexitapp.service.IndexFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/index-fields")
public class IndexFieldController {

    @Autowired
    private IndexFieldService service;

    @PostMapping
    public ResponseEntity<List<IndexField>> saveIndexFields(@RequestBody List<IndexField> fields) {
        List<IndexField> savedFields = service.saveAll(fields);
        return ResponseEntity.ok(savedFields);
    }
}
