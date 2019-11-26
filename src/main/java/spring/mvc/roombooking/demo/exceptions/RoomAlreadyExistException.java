package spring.mvc.roombooking.demo.exceptions;

public class RoomAlreadyExistException extends RuntimeException {
    public RoomAlreadyExistException(String name) {
        super("Room already exists: " + name);
    }
}
