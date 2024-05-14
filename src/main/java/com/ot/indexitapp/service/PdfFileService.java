package com.ot.indexitapp.service;

import com.ot.indexitapp.model.FileDetail;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PdfFileService {

    public List<FileDetail> listPdfFiles(String directoryPath) {
        File folder = new File(directoryPath);
        return Arrays.stream(folder.listFiles())
                .filter(file -> file.isFile() && file.getName().endsWith(".pdf"))
                .map(file -> {
                    try {
                        Path path = Paths.get(directoryPath, file.getName()).toAbsolutePath().normalize();
                        UUID fileUUID = generateUUIDFromFilePath(path.toString());
                        return new FileDetail(file.getName(), path.toString(), fileUUID);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException("Problem generating file UUID", e);
                    }
                })
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

    public UUID generateUUIDFromFilePath(String filePath) throws NoSuchAlgorithmException {
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(filePath.getBytes());
        return UUID.nameUUIDFromBytes(salt.digest());
    }
}
