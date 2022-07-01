package exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String name) {
        super(String.format("User with name '%s' already exists!", name));
    }
}
