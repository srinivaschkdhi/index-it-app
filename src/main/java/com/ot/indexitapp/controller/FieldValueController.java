package com.ot.indexitapp.controller;

import com.ot.indexitapp.model.FieldValue;
import com.ot.indexitapp.service.FieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/field-values")
public class FieldValueController {
    @Autowired
    private FieldValueService fieldValueService;

    @PostMapping("/batch")
    public ResponseEntity<List<FieldValue>> saveOrUpdateFieldValues(@RequestBody List<FieldValue> values) {
        List<FieldValue> savedValues = fieldValueService.saveOrUpdateFieldValues(values);
        return ResponseEntity.ok(savedValues);
    }
}