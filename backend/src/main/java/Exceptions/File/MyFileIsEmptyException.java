package Exceptions.File;

public class MyFileIsEmptyException extends RuntimeException{
    public MyFileIsEmptyException() {

    }

    public MyFileIsEmptyException(String message) {
        super(message);
    }
}
