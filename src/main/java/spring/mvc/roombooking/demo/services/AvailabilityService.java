package spring.mvc.roombooking.demo.services;

import java.util.Date;

public interface AvailabilityService {
    boolean isAvailable(String roomName, String datefrom, String dateto);
    Date convertStringToDate(String dateString);
    boolean isAvailableNow(String roomName);
}
