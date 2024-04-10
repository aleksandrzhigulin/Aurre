package com.ancapybara.aurre.service.impl;

import com.ancapybara.aurre.domain.entity.User;
import com.ancapybara.aurre.exception.File.MyFileIsEmptyException;
import com.ancapybara.aurre.service.FileService;
import com.ancapybara.aurre.exception.File.MyFileNotFoundException;
import com.ancapybara.aurre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceDefault implements FileService {

  private final UserRepository userRepository;

  private static final Path LOCATION = Paths.get("src/main/resources/uploads");

  @Autowired
  public FileServiceDefault(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

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
  public String uploadAvatar(Principal principal, MultipartFile file) {
    Optional<User> user = userRepository.findByUsername(principal.getName());
    String filename = upload(file);
    if (user.isPresent()) {
      user.get().setAvatarFilename(filename);
      userRepository.save(user.get());
    } else {
      throw new RuntimeException("User not found");
    }
    return filename;
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
