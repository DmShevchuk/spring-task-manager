package exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String id) {
        super(String.format("User with id=%s does not exist!", id));
    }
}
