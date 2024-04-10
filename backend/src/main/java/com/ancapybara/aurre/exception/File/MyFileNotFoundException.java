package com.ancapybara.aurre.exception.File;

public class MyFileNotFoundException extends RuntimeException {

  public MyFileNotFoundException() {

  }

  public MyFileNotFoundException(String message) {
    super(message);
  }
}
