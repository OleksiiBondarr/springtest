package spring.mvc.roombooking.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.mvc.roombooking.demo.dto.BookingDto;
import spring.mvc.roombooking.demo.entities.Booking;
import spring.mvc.roombooking.demo.services.BookingService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    BookingController( BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/all/{datefrom}/{dateto}")
    List<BookingDto> getBookings(@PathVariable String datefrom, @PathVariable String dateto) {
        return bookingService.getBookings(datefrom, dateto);
    }

    @GetMapping("/room/{name}/{datefrom}/{dateto}")
    List<BookingDto> getBookingByRoom(@PathVariable String name, @PathVariable String datefrom, @PathVariable String dateto) {
        return bookingService.getBookingByRoom(name, datefrom, dateto);
    }

    @GetMapping("/user/{login}/{datefrom}/{dateto}")
    List<BookingDto> getBookingByUser(@PathVariable String login, @PathVariable String datefrom, @PathVariable String dateto) {
        return bookingService.getBookingByUser(login, datefrom, dateto);
    }

    @PostMapping
    BookingDto postBooking(@RequestBody HashMap<String,Object> newBooking)
    {
        return bookingService.postBooking(newBooking);
    }


}
