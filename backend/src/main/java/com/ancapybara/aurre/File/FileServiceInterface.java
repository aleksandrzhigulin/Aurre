package com.ancapybara.aurre.File;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileServiceInterface {

  String upload(MultipartFile file);

  File get(String filename);

  boolean exists(String filename);
}
