package exceptions;

import java.io.IOException;

public class UnableToReadFileException extends IOException {
    public UnableToReadFileException(String message){
        super(message);
    }
}
