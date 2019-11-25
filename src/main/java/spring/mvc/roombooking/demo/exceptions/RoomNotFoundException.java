package spring.mvc.roombooking.demo.exceptions;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(Long id) {
        super("Could not find room " + id);
    }

    public RoomNotFoundException(String login) {
        super("Could not find room " + login);
    }
}