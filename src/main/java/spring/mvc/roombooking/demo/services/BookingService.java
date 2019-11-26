package spring.mvc.roombooking.demo.services;

import spring.mvc.roombooking.demo.dto.BookingDto;
import spring.mvc.roombooking.demo.dto.BookingPassDto;

import java.util.List;

public interface BookingService {
    List<BookingDto> getBookingByRoom(String name, String datefrom, String dateto);

    List<BookingDto> getBookings(String datefrom, String dateto);

    List<BookingDto> getBookingByUser(String login, String datefrom, String dateto);

    BookingDto postBooking(BookingPassDto newBooking);

}
