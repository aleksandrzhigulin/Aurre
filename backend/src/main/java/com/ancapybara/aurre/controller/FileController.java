package com.ancapybara.aurre.controller;

import com.ancapybara.aurre.exception.File.MyFileNotFoundException;
import com.ancapybara.aurre.service.FileService;

import java.security.Principal;

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

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/files/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String filename = fileService.upload(file);
            return ResponseEntity.ok(filename);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping("/files/upload/avatar/")
    public ResponseEntity<?> uploadAvatar(Principal principal, @RequestParam("file") MultipartFile file) {
        try {
            String filename = fileService.uploadAvatar(principal, file);
            return ResponseEntity.ok(filename);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
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
