package Exceptions.File;

public class MyFileNotFoundException extends RuntimeException{
    public MyFileNotFoundException() {

    }

    public MyFileNotFoundException(String message) {
        super(message);
    }
}
