package spring.mvc.roombooking.demo.services;

import spring.mvc.roombooking.demo.dto.BookingDto;
import spring.mvc.roombooking.demo.entities.Booking;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface BookingService {
    List<BookingDto> getBookingByRoom(String name, String datefrom, String dateto);
    List<BookingDto> getBookings(String datefrom, String dateto);
    List<BookingDto> getBookingByUser(String login, String datefrom, String dateto);
    BookingDto postBooking(HashMap<String,Object> newBooking);

}
