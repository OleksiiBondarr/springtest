package spring.mvc.roombooking.demo.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String login) {
        super("Could not find user " + login);
    }
}