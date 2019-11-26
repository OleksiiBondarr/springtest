package spring.mvc.roombooking.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.dto.BookingDto;
import spring.mvc.roombooking.demo.dto.BookingPassDto;
import spring.mvc.roombooking.demo.entities.Booking;
import spring.mvc.roombooking.demo.exceptions.RoomIsNotAvailableException;
import spring.mvc.roombooking.demo.exceptions.RoomNotFoundException;
import spring.mvc.roombooking.demo.exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.repositories.BookingRepository;
import spring.mvc.roombooking.demo.repositories.RoomRepository;
import spring.mvc.roombooking.demo.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final AvailabilityService availabilityService;

    @Autowired
    public BookingServiceImpl(BookingRepository repository,
                              UserRepository userRepository,
                              RoomRepository roomRepository,
                              AvailabilityService availabilityService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.availabilityService = availabilityService;
    }

    @Override
    public List<BookingDto> getBookings(String datefrom, String dateto) {
        return repository.findAll()
                .stream()
                .filter(booking -> !this.availabilityService.isAvailable(booking.getRoomName(), datefrom, dateto))
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingByRoom(String name, String datefrom, String dateto) {
        if (roomRepository.findById(name).isPresent()) {
            return repository
                    .findAll()
                    .stream()
                    .filter(booking -> booking.getRoomName().equals(name) && !this.availabilityService.isAvailable(booking.getRoomName(), datefrom, dateto))
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } else
            throw new RoomNotFoundException(name);
    }

    @Override
    public List<BookingDto> getBookingByUser(String login, String datefrom, String dateto) {
        if (userRepository.findById(login).isPresent())
            return repository
                    .findAll()
                    .stream().filter(booking -> booking.getLogin().equals(login) && !this.availabilityService.isAvailable(booking.getRoomName(), datefrom, dateto))
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        else
            throw new UserNotFoundException(login);
    }

    @Override
    public BookingDto postBooking(BookingPassDto newBooking) {
        if (!userRepository.findById(newBooking.getLogin()).isPresent())
            throw new UserNotFoundException(newBooking.getLogin());
        if (!roomRepository.findById(newBooking.getRoomName()).isPresent())
            throw new RoomNotFoundException(newBooking.getRoomName());
        if (BCrypt.checkpw(newBooking.getPassword(), userRepository.findById(newBooking.getLogin()).get().getPassword())) {
            Booking booking = new Booking(newBooking.getLogin(),
                    newBooking.getRoomName(),
                    this.availabilityService.convertStringToDate(newBooking.getFromdate()),
                    this.availabilityService.convertStringToDate(newBooking.getTodate()));
            if (this.availabilityService.isAvailable(booking.getRoomName(), newBooking.getFromdate(), newBooking.getTodate())) {
                repository.save(booking);
                return this.convertToDto(booking);
            }
            throw new RoomIsNotAvailableException(newBooking.getRoomName(),
                    newBooking.getFromdate(),
                    newBooking.getTodate());
        } else {
            throw new UserNotFoundException(newBooking.getLogin());
        }
    }


    private BookingDto convertToDto(Booking booking) {
        return new BookingDto(booking.getLogin(), booking.getRoomName(), booking.getFromdate().toString(), booking.getTodate().toString());
    }
}
