package com.ot.indexitapp.service;

import org.springframework.stereotype.Service;

import java.io.File;
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
}
