package com.ancapybara.aurre.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/files/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String uuid = file.getName() + UUID.randomUUID();
        fileService.upload(file);
        return ResponseEntity.ok("Success");
    }
}
