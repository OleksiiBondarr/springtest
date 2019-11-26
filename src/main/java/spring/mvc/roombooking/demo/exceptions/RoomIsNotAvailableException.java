package spring.mvc.roombooking.demo.exceptions;

public class RoomIsNotAvailableException extends RuntimeException {
    public RoomIsNotAvailableException(String name, String datefrom, String dateto) {
        super("Room is not available for the asked time: " + name + " " + datefrom + " " + dateto);
    }
}
