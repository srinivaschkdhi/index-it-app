package com.ot.indexitapp.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfFileService {

    public List<String> listPdfFiles(String directoryPath) {
        File folder = new File(directoryPath);
        return Arrays.stream(folder.listFiles())
                .filter(file -> file.isFile() && file.getName().endsWith(".pdf"))
                .map(File::getName)
                .collect(Collectors.toList());
    }

    public Resource loadFileAsResource(String directoryPath, String fileName) {
        try {
            Path fileStorageLocation = Paths.get(directoryPath).toAbsolutePath().normalize();
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }
}
