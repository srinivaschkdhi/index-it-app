package com.ot.indexitapp.model;

import java.util.UUID;

public class FileDetail {
    private String name;
    private String path;
    private UUID id;

    public FileDetail(String name, String path, UUID id) {
        this.name = name;
        this.path = path;
        this.id = id;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
