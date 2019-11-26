package spring.mvc.roombooking.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.entities.Booking;
import spring.mvc.roombooking.demo.repositories.BookingRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final BookingRepository repository;

    @Autowired
    public AvailabilityServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isAvailable(String roomName, String datefrom, String dateto) {
        Date datefromDate = datefrom.equals("null") ? null : this.convertStringToDate(datefrom);
        Date datetoDate = dateto.equals("null") ? null : this.convertStringToDate(dateto);

        List<Booking> bookings = repository.findAll().stream().filter(booking -> booking.getRoomName().equals(roomName))
                .collect(Collectors.toList());
        if (bookings.size() == 0) {
            return true;
        } else {
            List<Booking> notAvailableBookings = bookings.stream().filter(booking -> {
                if ((datefromDate == null) && (datetoDate == null)) return true;
                if (datefromDate == null) return (datetoDate.compareTo(booking.getFromdate()) >= 0);
                if (datetoDate == null) return (datefromDate.compareTo(booking.getTodate()) <= 0);
                return (((booking.getFromdate().compareTo(datefromDate) >= 0) && (booking.getFromdate().compareTo(datetoDate) <= 0)) ||
                        ((booking.getTodate().compareTo(datefromDate) >= 0) && (booking.getTodate().compareTo(datetoDate) <= 0)) ||
                        ((datefromDate.compareTo(booking.getFromdate()) >= 0) && (datefromDate.compareTo(booking.getTodate()) <= 0)) ||
                        ((datetoDate.compareTo(booking.getFromdate()) >= 0) && (datetoDate.compareTo(booking.getTodate()) <= 0))
                );
            }).collect(Collectors.toList());
            return notAvailableBookings.size() == 0;
        }
    }

    @Override
    public Date convertStringToDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isAvailableNow(String roomName) {
        List<Booking> bookings = repository.findAll().stream().filter(booking -> booking.getRoomName().equals(roomName))
                .collect(Collectors.toList());
        if (bookings.size() == 0) {
            return true;
        } else {
            Date currentDate = new Date();
            List<Booking> notAvailableBookings = bookings.stream().filter(booking ->
                    ((booking.getFromdate().compareTo(currentDate) <= 0) && (booking.getTodate().compareTo(currentDate) >= 0))
            ).collect(Collectors.toList());
            return notAvailableBookings.size() == 0;
        }
    }
}
