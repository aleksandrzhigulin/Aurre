package com.ancapybara.aurre.Exceptions.File;

public class MyFileExistsException extends RuntimeException {

  public MyFileExistsException() {

  }

  public MyFileExistsException(String message) {
    super(message);
  }
}
