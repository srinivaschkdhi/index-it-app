package com.ot.indexitapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "field_values")
public class FieldValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fieldValue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_definition_id", referencedColumnName = "id")
    private FieldDefinition fieldDefinition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String value) {
        this.fieldValue = value;
    }

    public FieldDefinition getFieldDefinition() {
        return fieldDefinition;
    }

    public void setFieldDefinition(FieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }
}
