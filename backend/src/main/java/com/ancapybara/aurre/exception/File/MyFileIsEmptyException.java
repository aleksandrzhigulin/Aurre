package com.ancapybara.aurre.exception.File;

public class MyFileIsEmptyException extends RuntimeException {

  public MyFileIsEmptyException() {

  }

  public MyFileIsEmptyException(String message) {
    super(message);
  }
}
