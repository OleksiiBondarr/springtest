package spring.mvc.roombooking.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.entities.Room;
import spring.mvc.roombooking.demo.exceptions.RoomAlreadyExistException;
import spring.mvc.roombooking.demo.exceptions.RoomNotFoundException;
import spring.mvc.roombooking.demo.exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.repositories.RoomRepository;
import spring.mvc.roombooking.demo.dto.RoomDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;
    private final AvailabilityService availabilityService;
    @Autowired
    public RoomServiceImpl(RoomRepository repository, AvailabilityService availabilityService) {
        this.repository = repository;
        this.availabilityService = availabilityService;
    }

    @Override
    public List<RoomDto> getRooms() {
        return repository
                .findAll()
                .stream()
                .filter(room -> this.availabilityService.isAvailableNow(room.getName()))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto postRoom(RoomDto newRoom) {
        if (!repository.findById(newRoom.getName()).isPresent()){
            Room room = new Room(newRoom.getName(),
                    newRoom.getLocation(),
                    newRoom.getNumberOfSeats(),
                    newRoom.isProjector(),
                    newRoom.getPhoneNumber()
            );
            repository.save(room);
            return this.convertToDto(room);
        }else {
            throw new RoomAlreadyExistException(newRoom.getName());
        }}
    @Override
    public RoomDto getRoom(String name) {
        if(repository.findById(name).isPresent())
            return this.convertToDto(repository.findById(name).get());
        else {
            throw new RoomNotFoundException(name);
        }
    }

    @Override
    public RoomDto updateRoom(RoomDto newRoom) {
        if(repository.findById(newRoom.getName()).isPresent()) {
            Room room = this.convertFromDto(this.getRoom(newRoom.getName()));
            room.setLocation(newRoom.getLocation().equals("") ? room.getLocation() : newRoom.getLocation());
            room.setNumberOfSeats(newRoom.getNumberOfSeats().equals(0) ? room.getNumberOfSeats() : newRoom.getNumberOfSeats());
            room.setProjector(newRoom.isProjector());
            room.setPhoneNumber(newRoom.getPhoneNumber().equals("") ? room.getPhoneNumber() : newRoom.getPhoneNumber());
            repository.save(room);
            return this.convertToDto(room);
        }else {
            throw new RoomNotFoundException(newRoom.getName());
        }
    }

    @Override
    public void deleteRoom(String name) {
        if(repository.findById(name).isPresent())
            repository.deleteById(name);
        else {
            throw new RoomNotFoundException(name);
        }
    }

    private RoomDto convertToDto(Room room) {
        return new RoomDto(room.getName(),room.getLocation(),room.getNumberOfSeats(),room.isProjector(),room.getPhoneNumber());
    }
    private Room convertFromDto(RoomDto roomDto) {
        if(repository.findById(roomDto.getName()).isPresent())
            return repository.findById(roomDto.getName()).get();
        else
            throw new RoomNotFoundException(roomDto.getName());
    }
}
