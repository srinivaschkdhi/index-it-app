package com.ot.indexitapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "index_fields")
public class IndexField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value_field")
    private String valueField;

    // Constructors
    public IndexField() {}

    public IndexField(String name, String valueField) {
        this.name = name;
        this.valueField = valueField;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String value) {
        this.valueField = value;
    }
}
