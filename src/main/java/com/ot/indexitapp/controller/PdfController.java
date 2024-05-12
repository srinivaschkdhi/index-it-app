package com.ot.indexitapp.controller;

import com.ot.indexitapp.service.PdfFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PdfController {

    @Autowired
    private PdfFileService fileService;

    @GetMapping("/api/pdf/list")
    public ResponseEntity<List<String>> listPdfFiles(@RequestParam String path) {
        List<String> files = fileService.listPdfFiles(path);
        return ResponseEntity.ok(files);
    }
}
