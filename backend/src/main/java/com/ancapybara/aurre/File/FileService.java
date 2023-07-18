package com.ancapybara.aurre.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    private static final Path LOCATION = Paths.get("resources/uploads");
    public void upload(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            Files.copy(file.getInputStream(), LOCATION);

        } catch (Exception e) {

        }
    }
}
