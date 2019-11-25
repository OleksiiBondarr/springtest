package spring.mvc.roombooking.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mvc.roombooking.demo.Entities.Room;
import spring.mvc.roombooking.demo.Entities.User;
import spring.mvc.roombooking.demo.Exceptions.RoomNotFoundException;
import spring.mvc.roombooking.demo.Exceptions.UserNotFoundException;
import spring.mvc.roombooking.demo.Repositories.RoomRepository;
import spring.mvc.roombooking.demo.Repositories.UserRepository;
import spring.mvc.roombooking.demo.dto.RoomDto;
import spring.mvc.roombooking.demo.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;
    @Autowired
    public RoomServiceImpl(RoomRepository repository){
        this.repository = repository;
    }

    @Override
    public List<RoomDto> getRooms() {
        return repository.findAll().stream().filter(Room::isAvailable).map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RoomDto postRoom(Room newRoom) {//TODO check for unique
        repository.save(newRoom);
        return this.convertToDto(newRoom);
    }

    private RoomDto getRoom(String name) {
        List<Room> rooms = repository.findAll().stream().filter(room -> room.getName().equals(name)).collect(Collectors.toList());
        if (rooms.size()==1){
            return this.convertToDto(rooms.get(0));
        }
        else {
            throw new RoomNotFoundException(name);
        }
    }

    @Override
    public RoomDto updateRoom(Room newRoom, String name) {
        Room room = this.convertFromDto(this.getRoom(name));
        room.setName(newRoom.getName().equals("") ? room.getName() : newRoom.getName());
        room.setLocation(newRoom.getLocation().equals("") ? room.getLocation() : newRoom.getLocation());
        room.setNumberOfSeats(newRoom.getNumberOfSeats().equals(0) ? room.getNumberOfSeats() : newRoom.getNumberOfSeats());
        room.setProjector(newRoom.isProjector());
        room.setPhoneNumber(newRoom.getPhoneNumber().equals("") ? room.getPhoneNumber() : newRoom.getPhoneNumber());
        repository.save(room);
        return this.convertToDto(room);
    }

    @Override
    public void deleteRoom(String name) {
        Room room = this.convertFromDto(getRoom(name));
        repository.deleteById(room.getId());
    }


    private RoomDto convertToDto(Room room) {
        return new RoomDto(room.getName(),room.getLocation(),room.getNumberOfSeats(),room.isProjector(),room.getPhoneNumber());
    }
    private Room convertFromDto(RoomDto roomDto) {
        return repository.findAll().stream().filter(room -> room.getName().equals(roomDto.getName())).collect(Collectors.toList()).get(0);
    }
}
