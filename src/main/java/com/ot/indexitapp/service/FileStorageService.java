package com.ot.indexitapp.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("C:/Users/Srinivas Chintakindh/Downloads/index-store");

    public String saveFileToPermanentLocation(String tempFilePath) throws IOException {
        Path sourcePath = Paths.get(tempFilePath);
        Path targetPath = this.rootLocation.resolve(sourcePath.getFileName());

        // Ensure the directory exists
        Files.createDirectories(rootLocation);

        // Copy the file to the new location (or use move instead of copy)
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetPath.toString();
    }
}
