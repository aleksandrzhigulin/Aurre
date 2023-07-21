package com.ancapybara.aurre.File;

import Exceptions.File.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
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
        String filename;
        try {
            filename = fileService.upload(file);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error", HttpStatus.SERVICE_UNAVAILABLE);
        }

        return ResponseEntity.ok(filename);
    }

    @GetMapping("/files/get/{filename}")
    public ResponseEntity<?> getFile(@PathVariable("filename") String filename) {
        File file;
        try {
            file = fileService.get(filename);
        } catch (MyFileNotFoundException myFileNotFoundException) {
            return new ResponseEntity<>(myFileNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
        try {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(new UrlResource(file.toURI()));
        } catch (MalformedURLException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
}
