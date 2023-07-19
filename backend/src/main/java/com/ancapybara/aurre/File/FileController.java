package com.ancapybara.aurre.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.net.MalformedURLException;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/files/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String filename = fileService.upload(file);
        return ResponseEntity.ok(filename);
    }

    @GetMapping("/files/get/{filename}")
    public ResponseEntity<?> getFile(@PathVariable("filename") String filename) throws MalformedURLException {
        File file = fileService.get(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(new UrlResource(file.toURI()));
    }
}
