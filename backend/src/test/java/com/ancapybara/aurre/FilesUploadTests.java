package com.ancapybara.aurre;

import com.ancapybara.aurre.File.FileService;
import java.io.File;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;


public class FilesUploadTests {


  private FileService fileService = new FileService();

  @Test
  public void checkCVExists() {
    Assertions.assertTrue(fileService.exists("CVFinal.pdf"));
  }

}
