package spring.mvc.roombooking.demo.exceptions;

public class PasswordIsToShortException extends RuntimeException {
    public PasswordIsToShortException() { super("Password should be longer than 6 characters");
    }
}
