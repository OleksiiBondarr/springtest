package spring.mvc.roombooking.demo.exceptions;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(String login) {
        super("Could not find room " + login);
    }
}