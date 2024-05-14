package com.ot.indexitapp.controller;

import com.ot.indexitapp.model.FileDetail;
import com.ot.indexitapp.service.FileStorageService;
import com.ot.indexitapp.service.PdfFileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PdfController {

    @Autowired
    private PdfFileService fileService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/api/pdf/files")
    public ResponseEntity<List<FileDetail>> getFiles(@RequestParam String directoryPath) {
        List<FileDetail> files = fileService.listPdfFiles(directoryPath);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/api/pdf/files/{filename}")
    public ResponseEntity<Resource> getFile(
            @PathVariable String filename,
            @RequestParam(required = false, defaultValue = "default/path") String path,
            HttpServletRequest request
    ) {
        Resource resource = fileService.loadFileAsResource(path, filename);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @PostMapping("/save-file")
    public String saveFile(@RequestParam String filePath) {
        try {
            String savedFilePath = fileStorageService.saveFileToPermanentLocation(filePath);
            return "File saved successfully to: " + savedFilePath;
        } catch (Exception e) {
            return "Error saving file: " + e.getMessage();
        }
    }


}
