package com.ancapybara.aurre.exception.File;

public class MyFileExistsException extends RuntimeException {

  public MyFileExistsException() {

  }

  public MyFileExistsException(String message) {
    super(message);
  }
}
