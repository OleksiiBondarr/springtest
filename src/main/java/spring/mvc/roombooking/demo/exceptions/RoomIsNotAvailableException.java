package spring.mvc.roombooking.demo.exceptions;

public class RoomIsNotAvailableException extends RuntimeException {
    public RoomIsNotAvailableException(String login, String datefrom, String dateto) {
        super("Room is not available for the asked time: " + login + " " + datefrom +  " " + dateto);
    }
}
