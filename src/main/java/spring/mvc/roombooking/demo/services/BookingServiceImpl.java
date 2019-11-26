package spring.mvc.roombooking.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.dto.BookingDto;
import spring.mvc.roombooking.demo.entities.Booking;
import spring.mvc.roombooking.demo.exceptions.RoomIsNotAvailableException;
import spring.mvc.roombooking.demo.exceptions.RoomNotFoundException;
import spring.mvc.roombooking.demo.exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.repositories.BookingRepository;
import spring.mvc.roombooking.demo.repositories.RoomRepository;
import spring.mvc.roombooking.demo.repositories.UserRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
                              AvailabilityService availabilityService){
        this.repository = repository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.availabilityService = availabilityService;
    }

    @Override
    public List<BookingDto> getBookings(String datefrom, String dateto) {
        return repository.findAll()
                .stream()
                .filter(booking -> !this.availabilityService.isAvailable(booking.getRoomName(), datefrom,dateto))
                .map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingByRoom(String name, String datefrom, String dateto) {
        if (roomRepository.findById(name).isPresent())
            return repository
                .findAll()
                .stream()
                .filter(booking -> booking.getRoomName().equals(name)&&!this.availabilityService.isAvailable(booking.getRoomName(), datefrom,dateto))
                .map(this::convertToDto)
                .collect(Collectors.toList());
        else
            throw new RoomNotFoundException(name);//TODO FIX bug, throws User not found Exception
    }

    @Override
    public List<BookingDto> getBookingByUser(String login, String datefrom, String dateto) {
        if (userRepository.findById(login).isPresent())
            return repository
                .findAll()
                .stream().filter(booking -> booking.getLogin().equals(login)&&!this.availabilityService.isAvailable(booking.getRoomName(), datefrom, dateto))
                .map(this::convertToDto)
                .collect(Collectors.toList());
        else
            throw new UserNotFoundException(login);
    }

    @Override
    public BookingDto postBooking(HashMap<String,Object> newBooking) {
        if(!userRepository.findById((String)newBooking.get("login")).isPresent())
            throw new UserNotFoundException((String)newBooking.get("login"));
        if(!roomRepository.findById((String)newBooking.get("name")).isPresent())
            throw new RoomNotFoundException((String)newBooking.get("name"));
        if(BCrypt.checkpw((String)newBooking.get("password"), userRepository.findById((String)newBooking.get("login")).get().getPassword())){
            Booking booking = new Booking((String) newBooking.get("name"),
                    (String) newBooking.get("login"),
                    this.availabilityService.convertStringToDate((String) newBooking.get("datefrom")),
                    this.availabilityService.convertStringToDate((String) newBooking.get("dateto")));
            if(this.availabilityService.isAvailable(booking.getRoomName(),(String) newBooking.get("datefrom"),(String)newBooking.get("dateto"))){
                repository.save(booking);
                return this.convertToDto(booking);
            }
            throw new RoomIsNotAvailableException((String)newBooking.get("login"),
                    (String)newBooking.get("datefrom"),
                    (String)newBooking.get("dateto"));
        }
        else{
            throw new UserNotFoundException((String)newBooking.get("login"));
        }
    }


    private BookingDto convertToDto(Booking booking) {
        return new BookingDto(booking.getLogin(),booking.getRoomName(),booking.getFromdate().toString(),booking.getTodate().toString());
    }
}
