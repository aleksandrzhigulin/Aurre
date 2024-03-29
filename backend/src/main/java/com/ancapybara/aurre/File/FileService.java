package com.ancapybara.aurre.File;

import com.ancapybara.aurre.Exceptions.File.MyFileIsEmptyException;
import com.ancapybara.aurre.Exceptions.File.MyFileNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService implements FileServiceInterface {

  private static final Path LOCATION = Paths.get("src/main/resources/uploads");

  @Override
  public String upload(MultipartFile file) {
    try {
      if (file.isEmpty()) {
        throw new MyFileIsEmptyException("File is empty");
      }
      String filename = UUID.randomUUID() + file.getOriginalFilename();
      Files.copy(file.getInputStream(), LOCATION.resolve(Objects.requireNonNull(filename)));
      return filename;
    } catch (IOException e) {
      throw new RuntimeException("Error");
    }
  }

  @Override
  public boolean exists(String filename) {
    File file = LOCATION.resolve(filename).toFile();
    return file.exists();
  }

  @Override
  public File get(String filename) {
    if (!exists(filename)) {
      throw new MyFileNotFoundException("File not found");
    }
    return LOCATION.resolve(filename).toFile();
  }

}
