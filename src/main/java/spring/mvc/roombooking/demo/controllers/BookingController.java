package spring.mvc.roombooking.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.mvc.roombooking.demo.dto.BookingDto;
import spring.mvc.roombooking.demo.dto.BookingPassDto;
import spring.mvc.roombooking.demo.services.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/all")
    List<BookingDto> getBookings(@RequestParam String fromdate, @RequestParam String todate) {
        return bookingService.getBookings(fromdate, todate);
    }

    @GetMapping("/room")
    List<BookingDto> getBookingByRoom(@RequestParam String name, @RequestParam String fromdate, @RequestParam String todate) {
        return bookingService.getBookingByRoom(name, fromdate, todate);
    }

    @GetMapping("/user")
    List<BookingDto> getBookingByUser(@RequestParam String login, @RequestParam String fromdate, @RequestParam String todate) {
        return bookingService.getBookingByUser(login, fromdate, todate);
    }

    @PostMapping
    BookingDto postBooking(@RequestBody BookingPassDto newBooking) {
        return bookingService.postBooking(newBooking);
    }


}
