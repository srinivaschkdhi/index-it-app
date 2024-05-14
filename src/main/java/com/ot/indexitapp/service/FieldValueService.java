package com.ot.indexitapp.service;

import com.ot.indexitapp.model.FieldValue;
import com.ot.indexitapp.repository.FieldDefinitionRepository;
import com.ot.indexitapp.repository.FieldValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldValueService {
    @Autowired
    private FieldValueRepository fieldValueRepository;
    @Autowired
    private FieldDefinitionRepository fieldDefinitionRepository;

    @Transactional
    public List<FieldValue> saveOrUpdateFieldValues(List<FieldValue> newValues) {
        return newValues.stream().map(newValue -> {
            return fieldDefinitionRepository.findById(newValue.getFieldDefinition().getId()).map(definition -> {
                FieldValue existingValue = definition.getFieldValue();
                if (existingValue != null) {
                    existingValue.setFieldValue(newValue.getFieldValue());
                } else {
                    existingValue = newValue;
                    existingValue.setFieldDefinition(definition);
                    definition.setFieldValue(existingValue);
                }
                return fieldValueRepository.save(existingValue);
            }).orElseThrow(() -> new RuntimeException("Field Definition not found with id: " + newValue.getFieldDefinition().getId()));
        }).collect(Collectors.toList());
    }
}
