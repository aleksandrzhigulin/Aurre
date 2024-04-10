package com.ancapybara.aurre.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;

public interface FileService {

  String upload(MultipartFile file);

  String uploadAvatar(Principal principal, MultipartFile file);

  File get(String filename);

  boolean exists(String filename);

}
